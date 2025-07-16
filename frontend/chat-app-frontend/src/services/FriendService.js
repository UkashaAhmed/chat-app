const BASE_URL = "http://localhost:8082/api/friends";

// Get all accepted friends for a user

// Get all users
export const getAllUsers = async () => {
  const response = await fetch("http://localhost:8081/api/auth/users/all");
  if (!response.ok) throw new Error("Failed to fetch users");
  return await response.json();
};

// ✅ Send friend request using JSON body
export const sendFriendRequest = async (senderId, recipientId) => {
  const response = await fetch(`${BASE_URL}/request`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ senderId, recipientId }),
  });

  if (!response.ok) throw new Error("Failed to send friend request");
  return await response.json();
};

// Accept friend request
export const acceptFriendRequest = async (requestId) => {
  const response = await fetch(`${BASE_URL}/accept/${requestId}`, { method: "PUT" });
  if (!response.ok) throw new Error("Failed to accept request");
  return await response.json();
};

export const getPendingRequests = async (userId) => {
  const response = await fetch(`${BASE_URL}/pending/${userId}`);
  if (!response.ok) throw new Error("Failed to fetch pending requests");
  return await response.json();
};
