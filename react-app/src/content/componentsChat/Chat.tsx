// src/components/Chat.js
import React, { useEffect, useState } from 'react';
import io from 'socket.io-client';

const socket = io('http://localhost:8081?mac=2');

const Chat = () => {
    const [messages, setMessages] = useState([]);
    const [input, setInput] = useState('');

    useEffect(() => {
        socket.on('connect', () => {
            console.log('Connected to server');
        });

        socket.on('messageevent', (message) => {
            // @ts-ignore
            setMessages((prevMessages) => [...prevMessages, message]);
        });

        socket.on('disconnect', () => {
            console.log('Disconnected from server');
        });

        return () => {
            socket.off('connect');
            socket.off('message');
            socket.off('disconnect');
        };
    }, []);

    const sendMessage = () => {
        console.log('sendMessage');
        if (input.trim()) {
            socket.emit('messageevent', input);
            setInput('');
        }
    };

    return (
        <div>
            <div>
                <h2>Chat Messages</h2>
                <div>
                    {messages.map((msg, index) => (
                        <div key={index}>{msg}</div>
                    ))}
                </div>
            </div>
            <div>
                <input
                    type="text"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                />
                <button onClick={sendMessage}>Send</button>
            </div>
        </div>
    );
};

export default Chat;
