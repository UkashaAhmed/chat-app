import React, { useState } from 'react';

const MessageInput = ({ chatId, onMessageSent }) => {
  const [content, setContent] = useState('');

  const handleSend = async () => {
    const response = await fetch('http://localhost:8084/api/messages', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify({ chatId, content })
    });
    const data = await response.json();
    onMessageSent(data);
    setContent('');
  };

  return (
    <div style={{ display: 'flex' }}>
      <input
        style={{ flex: 1, padding: '0.5rem' }}
        value={content}
        onChange={(e) => setContent(e.target.value)}
        placeholder="Type your message..."
      />
      <button onClick={handleSend} style={{ padding: '0.5rem 1rem' }}>Send</button>
    </div>
  );
};

export default MessageInput;
