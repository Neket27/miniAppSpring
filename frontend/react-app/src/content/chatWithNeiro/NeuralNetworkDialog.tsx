import React, {useState, useRef, useContext} from 'react';
import '../../../css//NeuralNetworkDialog.css';
import {ContextService, State} from "../../main";
import {NeuroChatDto} from "../../model/neuroChat/NeuroChatDto"; // Importing the CSS file for custom styles

const NeuralNetworkDialog = ({ show, handleClose }) => {
    const contextService:State = useContext(ContextService);
    const [input, setInput] = useState('');
    const [messages, setMessages] = useState([]); // Состояние для хранения истории сообщений
    const dialogRef = useRef(null); // Ссылка на элемент диалога


    const getAnswerFromNeuroChat =async ()=>{
        const request :NeuroChatDto ={
            userId: 1,
            message:input
        };
        const  response = await contextService.neuroChatService.getAnswerFromNeuroChat(request);
        console.log(response);
            const userMessage = { text: input, sender: 'user' };
            const botResponse = { text: `Ответ на: ${response.message}`, sender: 'bot' }; // Здесь вы можете интегрировать вызов к нейронной сети

            // Обновляем историю сообщений
            setMessages((prevMessages) => [...prevMessages, userMessage, botResponse]);
            setInput(''); // Очищаем ввод

    }

    const handleInputChange = (e) => {
        setInput(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (input.trim()) {
            getAnswerFromNeuroChat();

        }
    };

    // Функция для изменения размера диалога
    const handleResize = (e) => {
        const newHeight = e.clientY - dialogRef.current.getBoundingClientRect().top;
        if (newHeight > 200 && newHeight < window.innerHeight * 0.8) { // Ограничиваем минимальную и максимальную высоту
            dialogRef.current.style.height = `${newHeight}px`;
        }
    };

    const handleMouseDown = (e) => {
        e.preventDefault();
        document.addEventListener('mousemove', handleResize);
        document.addEventListener('mouseup', () => {
            document.removeEventListener('mousemove', handleResize);
        }, { once: true });
    };

    if (!show) return null; // Не отображаем, если не показан

    return (
        <div className="neural-network-dialog" ref={dialogRef}>
            <div className="dialog-header">
                <h2>Чат с нейронной сетью</h2>
                <button onClick={handleClose} className="close-button">×</button>
            </div>
            <div className="messages">
                {messages.map((msg, index) => (
                    <div key={index} className={`message ${msg.sender}`}>
                        <span>{msg.text}</span>
                    </div>
                ))}
            </div>
            <form onSubmit={()=>null} className="message-form">
                <input
                    type="text"
                    value={input}
                    onChange={handleInputChange}
                    placeholder="Введите ваше сообщение"
                />
                <button onClick={handleSubmit}>Отправить</button>
            </form>
            <div className="resize-handle" onMouseDown={handleMouseDown} />
        </div>
    );
};

export default NeuralNetworkDialog;
