import React, { useEffect, useState, useRef } from "react";
import {
  getPendingRequests,
  sendFriendRequest,
  acceptFriendRequest,
} from "../services/FriendService";
import {
  createConversation,
  getConversations,
  getMessages,
} from "../services/ChatService";

const WEBSOCKET_URL = "ws://localhost:8083/ws";

const ChatDashboard = () => {
  const [currentUserId, setCurrentUserId] = useState("");
  const [uuidToAdd, setUuidToAdd] = useState("");
  const [conversations, setConversations] = useState([]);
  const [selectedConversation, setSelectedConversation] = useState(null);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState("");
  const [pendingRequests, setPendingRequests] = useState([]);
  const [conversationParticipant, setConversationParticipant] = useState("");
  const wsRef = useRef(null);

  useEffect(() => {
    const storedId = localStorage.getItem("userId");
    if (storedId) {
      setCurrentUserId(storedId);
      fetchPendingRequests(storedId);
      fetchConversations(storedId);
    }

    return () => {
      if (wsRef.current) wsRef.current.close();
    };
  }, []);

  useEffect(() => {
    if (!selectedConversation || !currentUserId) return;

    fetchMessages(selectedConversation.id);

    const ws = new WebSocket(WEBSOCKET_URL);
    wsRef.current = ws;

    ws.onopen = () => {
      console.log("WebSocket connected");
    };

    ws.onmessage = (event) => {
      try {
        const msg = JSON.parse(event.data);
        if (msg.conversationId === selectedConversation.id) {
          setMessages((prev) => [...prev, msg]);
        }
      } catch (e) {
        console.error("WebSocket message error:", e);
      }
    };

    ws.onerror = (e) => {
      console.error("WebSocket error", e);
    };

    ws.onclose = () => {
      console.log("WebSocket closed");
    };

    return () => {
      ws.close();
    };
  }, [selectedConversation, currentUserId]);

  const fetchPendingRequests = async (userId) => {
    try {
      const response = await getPendingRequests(userId);
      setPendingRequests(response);
    } catch (err) {
      console.error("Error fetching pending requests:", err);
    }
  };

  const fetchConversations = async (userId) => {
    try {
      const response = await getConversations(userId);
      setConversations(response);
    } catch (err) {
      console.error("Error fetching conversations:", err);
    }
  };

  const fetchMessages = async (conversationId) => {
    try {
      const response = await getMessages(conversationId);
      setMessages(response);
    } catch (err) {
      console.error("Error fetching messages:", err);
    }
  };

  const handleAddFriend = async () => {
    try {
      await sendFriendRequest(currentUserId, uuidToAdd);
      alert("Friend request sent!");
      setUuidToAdd("");
    } catch (err) {
      console.error("Failed to send friend request:", err);
    }
  };

  const handleAccept = async (requestId) => {
    try {
      await acceptFriendRequest(requestId);
      alert("Friend request accepted!");
      fetchPendingRequests(currentUserId);
    } catch (err) {
      console.error("Failed to accept request:", err);
    }
  };

  const handleCreateConversation = async () => {
    try {
      const participantIds = [currentUserId, conversationParticipant];
      await createConversation(participantIds);
      alert("Conversation created!");
      setConversationParticipant("");
      fetchConversations(currentUserId);
    } catch (err) {
      console.error("Failed to create conversation:", err);
    }
  };

  const handleSendMessage = () => {
    if (!selectedConversation || !wsRef.current || wsRef.current.readyState !== WebSocket.OPEN) return;

    const recipientId = selectedConversation.participantIds.find(
      (id) => id !== currentUserId
    );

    const messagePayload = {
      conversationId: selectedConversation.id,
      senderId: currentUserId,
      recipientId,
      content: newMessage,
      timestamp: new Date().toISOString(),
    };

    try {
      wsRef.current.send(JSON.stringify(messagePayload));
      setNewMessage("");
    } catch (err) {
      console.error("Failed to send message via WebSocket:", err);
    }
  };

  return (
    <div style={styles.dashboard}>
      <h2 style={styles.title}>💬 Chat Dashboard</h2>

      <div style={styles.grid}>
        {/* Left Panel */}
        <div style={styles.sidebar}>
          <div style={styles.card}>
            <h3>Add Friend</h3>
            <input
              style={styles.input}
              type="text"
              placeholder="Enter UUID"
              value={uuidToAdd}
              onChange={(e) => setUuidToAdd(e.target.value)}
            />
            <button style={styles.button} onClick={handleAddFriend}>
              ➕ Send Friend Request
            </button>
          </div>

          <div style={styles.card}>
            <h3>Pending Friend Requests</h3>
            <ul style={styles.list}>
              {pendingRequests.map((req) => (
                <li key={req.id}>
                  From: {req.senderId}
                  <button
                    style={styles.smallButton}
                    onClick={() => handleAccept(req.id)}
                  >
                    Accept
                  </button>
                </li>
              ))}
            </ul>
          </div>

          <div style={styles.card}>
            <h3>Create Conversation</h3>
            <input
              style={styles.input}
              type="text"
              placeholder="Enter participant UUID"
              value={conversationParticipant}
              onChange={(e) => setConversationParticipant(e.target.value)}
            />
            <button style={styles.button} onClick={handleCreateConversation}>
              💬 Start Conversation
            </button>
          </div>

          <div style={styles.card}>
            <h3>Your Conversations</h3>
            <ul style={styles.list}>
              {conversations.map((conv) => (
                <li key={conv.id}>
                  <button
                    style={styles.linkButton}
                    onClick={() => setSelectedConversation(conv)}
                  >
                    📁 ID: {conv.id}
                  </button>
                </li>
              ))}
            </ul>
          </div>
        </div>

        {/* Right Panel */}
        <div style={styles.chatArea}>
          {selectedConversation ? (
            <div style={styles.card}>
              <h3>Conversation: {selectedConversation.id}</h3>
              <div style={styles.messageList}>
                {messages.map((msg, idx) => (
                  <div
                    key={idx}
                    style={{
                      ...styles.message,
                      alignSelf:
                        msg.senderId === currentUserId
                          ? "flex-end"
                          : "flex-start",
                      backgroundColor:
                        msg.senderId === currentUserId ? "#cce5ff" : "#e2e3e5",
                    }}
                  >
                    <strong>{msg.senderId}</strong>: {msg.content}
                  </div>
                ))}
              </div>
              <div style={styles.messageInput}>
                <input
                  style={styles.input}
                  type="text"
                  placeholder="Type your message..."
                  value={newMessage}
                  onChange={(e) => setNewMessage(e.target.value)}
                />
                <button style={styles.button} onClick={handleSendMessage}>
                  📤 Send
                </button>
              </div>
            </div>
          ) : (
            <div style={styles.card}>
              <p>Select a conversation to start chatting!</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

const styles = {
  dashboard: {
    padding: "20px",
    fontFamily: "Segoe UI, sans-serif",
    backgroundColor: "#f4f6f9",
    minHeight: "100vh",
  },
  title: {
    fontSize: "2rem",
    marginBottom: "20px",
    textAlign: "center",
    color: "#333",
  },
  grid: {
    display: "flex",
    gap: "20px",
  },
  sidebar: {
    width: "300px",
    display: "flex",
    flexDirection: "column",
    gap: "20px",
  },
  chatArea: {
    flexGrow: 1,
  },
  card: {
    backgroundColor: "#fff",
    borderRadius: "12px",
    padding: "20px",
    boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
  },
  input: {
    padding: "10px",
    width: "100%",
    marginBottom: "10px",
    borderRadius: "8px",
    border: "1px solid #ccc",
  },
  button: {
    padding: "10px 15px",
    border: "none",
    borderRadius: "8px",
    backgroundColor: "#007bff",
    color: "#fff",
    cursor: "pointer",
    width: "100%",
    fontWeight: "bold",
  },
  smallButton: {
    marginLeft: "10px",
    padding: "5px 10px",
    border: "none",
    borderRadius: "6px",
    backgroundColor: "#28a745",
    color: "#fff",
    cursor: "pointer",
  },
  linkButton: {
    background: "none",
    border: "none",
    color: "#007bff",
    textDecoration: "underline",
    cursor: "pointer",
    padding: 0,
  },
  list: {
    listStyle: "none",
    paddingLeft: 0,
  },
  messageList: {
    display: "flex",
    flexDirection: "column",
    gap: "10px",
    maxHeight: "400px",
    overflowY: "auto",
    backgroundColor: "#f9f9f9",
    padding: "10px",
    borderRadius: "8px",
    marginBottom: "15px",
  },
  message: {
    padding: "10px",
    borderRadius: "8px",
    maxWidth: "70%",
  },
  messageInput: {
    display: "flex",
    gap: "10px",
  },
};

export default ChatDashboard;
