let sock = new SockJS('http:/ /localhost:8080/ws');

let client = Stomp.over(sock);

function sendMessage() {
    console.log("Sending message");
    let input = document.getElementById('message-input');
    client.send('/app/chat', {}, JSON.stringify({ content: input.value }));
}


client.connect({}, (frame) => {
    client.subscribe('/topic/messages', (payload) => {
        let message_list = document.getElementById('message-list');
        let message = document.createElement('li');
        let output = JSON.parse(payload.body);
        message.appendChild(document.createTextNode(output.content +" at " +output.time));
        message_list.appendChild(message);
    });
});