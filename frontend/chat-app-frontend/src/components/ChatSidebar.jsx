import React, { useEffect, useState } from 'react';

const ChatSidebar = ({ onSelectChat }) => {
  const [chats, setChats] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8083/api/chats/user', {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    })
      .then(res => res.json())
      .then(setChats)
      .catch(console.error);
  }, []);

  return (
    <div style={{ width: '25%', backgroundColor: '#f0f0f0', padding: '1rem' }}>
      <h3>Your Chats</h3>
      {chats.map(chat => (
        <div
          key={chat.id}
          style={{ padding: '0.5rem', cursor: 'pointer' }}
          onClick={() => onSelectChat(chat.id)}
        >
          {chat.name}
        </div>
      ))}
    </div>
  );
};

export default ChatSidebar;
