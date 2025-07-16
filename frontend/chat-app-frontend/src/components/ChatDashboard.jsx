import React, { useEffect, useState } from "react";
import {
  getAllUsers,
  sendFriendRequest,
  acceptFriendRequest,
  getPendingRequests,
} from "../services/FriendService";
import {
  createConversation,
  getConversations,
  getMessages,
  sendMessage,
} from "../services/ChatService";
import "./ChatDashboard.css";

const ChatDashboard = () => {
  const [currentUserId, setCurrentUserId] = useState("");
  const [uuidToAdd, setUuidToAdd] = useState("");
  const [conversationParticipant, setConversationParticipant] = useState("");
  const [conversations, setConversations] = useState([]);
  const [selectedConversation, setSelectedConversation] = useState(null);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState("");
  const [pendingRequests, setPendingRequests] = useState([]);

  useEffect(() => {
    const userId = localStorage.getItem("userId");
    if (userId) {
      setCurrentUserId(userId);
      fetchConversations(userId);
      fetchPendingRequests(userId);
    }
  }, []);

  const fetchPendingRequests = async (userId) => {
    try {
      const response = await getPendingRequests(userId);
      setPendingRequests(response);
    } catch (error) {
      console.error("Error fetching pending requests:", error);
    }
  };

  const fetchConversations = async (userId) => {
    try {
      const response = await getConversations(userId);
      setConversations(response);
    } catch (error) {
      console.error("Error fetching conversations:", error);
    }
  };

  const fetchMessages = async (conversationId) => {
    try {
      const response = await getMessages(conversationId);
      setMessages(response);
    } catch (error) {
      console.error("Error fetching messages:", error);
    }
  };

  const handleAddFriend = async () => {
    try {
      await sendFriendRequest(currentUserId, uuidToAdd);
      alert("Friend request sent!");
      setUuidToAdd("");
    } catch (error) {
      console.error("Error sending friend request:", error);
    }
  };

  const handleAccept = async (requestId) => {
    try {
      await acceptFriendRequest(requestId);
      alert("Friend request accepted!");
      fetchPendingRequests(currentUserId);
    } catch (error) {
      console.error("Error accepting friend request:", error);
    }
  };

  const handleCreateConversation = async () => {
    try {
      await createConversation([currentUserId, conversationParticipant]);
      alert("Conversation created!");
      fetchConversations(currentUserId);
      setConversationParticipant("");
    } catch (error) {
      console.error("Error creating conversation:", error);
    }
  };

  const handleSendMessage = async () => {
    try {
      const recipientId = selectedConversation.participantIds.find(id => id !== currentUserId);
      await sendMessage(selectedConversation.id, currentUserId, recipientId, newMessage);
      setNewMessage("");
      fetchMessages(selectedConversation.id);
    } catch (error) {
      console.error("Error sending message:", error);
    }
  };

  return (
    <div className="dashboard-container">
      <h1>Chat Dashboard</h1>

      <div className="card">
        <h2>Send Friend Request</h2>
        <input
          type="text"
          placeholder="Enter UUID"
          value={uuidToAdd}
          onChange={(e) => setUuidToAdd(e.target.value)}
        />
        <button onClick={handleAddFriend}>Send Request</button>
      </div>

      <div className="card">
        <h2>Pending Friend Requests</h2>
        {pendingRequests.length === 0 ? (
          <p>No pending requests</p>
        ) : (
          <ul>
            {pendingRequests.map((req) => (
              <li key={req.id}>
                From: {req.senderId}
                <button onClick={() => handleAccept(req.id)}>Accept</button>
              </li>
            ))}
          </ul>
        )}
      </div>

      <div className="card">
        <h2>Create Conversation</h2>
        <input
          type="text"
          placeholder="Enter UUID to chat with"
          value={conversationParticipant}
          onChange={(e) => setConversationParticipant(e.target.value)}
        />
        <button onClick={handleCreateConversation}>Start Conversation</button>
      </div>

      <div className="chat-section">
        <div className="conversation-list">
          <h3>Conversations</h3>
          {conversations.map((conv) => (
            <button
              key={conv.id}
              className={`conversation-button ${selectedConversation?.id === conv.id ? "active" : ""}`}
              onClick={() => {
                setSelectedConversation(conv);
                fetchMessages(conv.id);
              }}
            >
              Conversation: {conv.id.substring(0, 6)}...
            </button>
          ))}
        </div>

        <div className="chat-box">
          <h3>Messages</h3>
          <div className="messages">
            {messages.map((msg, idx) => (
              <div
                key={idx}
                className={`message ${msg.senderId === currentUserId ? "sent" : "received"}`}
              >
                <span><strong>{msg.senderId}:</strong> {msg.content}</span>
              </div>
            ))}
          </div>
          {selectedConversation && (
            <div className="input-section">
              <input
                type="text"
                value={newMessage}
                onChange={(e) => setNewMessage(e.target.value)}
                placeholder="Type a message"
              />
              <button onClick={handleSendMessage}>Send</button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ChatDashboard;
