import React, { useEffect, useState } from 'react';
import MessageInput from './MessageInput';

const ChatWindow = ({ chatId }) => {
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    if (!chatId) return;
    fetch(`http://localhost:8084/api/messages/chat/${chatId}`, {
      headers: {
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    })
      .then(res => res.json())
      .then(setMessages)
      .catch(console.error);
  }, [chatId]);

  if (!chatId) {
    return <div style={{ flex: 1, padding: '1rem' }}>Select a chat to view messages.</div>;
  }

  return (
    <div style={{ flex: 1, display: 'flex', flexDirection: 'column', padding: '1rem' }}>
      <div style={{ flex: 1, overflowY: 'auto', marginBottom: '1rem' }}>
        {messages.map(msg => (
          <div key={msg.id} style={{ marginBottom: '0.5rem' }}>
            <strong>{msg.senderId}</strong>: {msg.content}
          </div>
        ))}
      </div>
      <MessageInput chatId={chatId} onMessageSent={msg => setMessages(prev => [...prev, msg])} />
    </div>
  );
};

export default ChatWindow;
