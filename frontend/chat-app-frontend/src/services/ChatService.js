const API_BASE = "http://localhost:8083/api/chats";

export const getConversations = async (userId) => {
  try {
    const response = await fetch(`${API_BASE}/conversations/${userId}`);
    if (!response.ok) {
      throw new Error("Failed to fetch conversations");
    }
    return await response.json();
  } catch (error) {
    console.error("Error fetching chats:", error);
    throw error;
  }
};

export const createConversation = async (participantIds) => {
  try {
    const response = await fetch(`${API_BASE}/conversations`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ participantIds }), // ✅ correct shape
    });

    if (!response.ok) {
      throw new Error("Failed to create conversation");
    }
    return await response.json();
  } catch (error) {
    console.error("Error creating conversation:", error);
    throw error;
  }
};


export const getMessages = async (conversationId) => {
  try {
    const response = await fetch(`${API_BASE}/messages/${conversationId}`);
    if (!response.ok) {
      throw new Error("Failed to fetch messages");
    }
    return await response.json();
  } catch (error) {
    console.error("Error fetching messages:", error);
    throw error;
  }
};

export const sendMessage = async (conversationId, senderId, recipientId, content) => {
  try {
    const response = await fetch(`${API_BASE}/messages/${conversationId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ senderId, recipientId, content }),
    });

    if (!response.ok) {
      throw new Error("Failed to send message");
    }

    return await response.json();
  } catch (error) {
    console.error("Error sending message:", error);
    throw error;
  }
};
