# Rsocket

## Communication Models
- Fire and Forget:  a client sends one message and expects no response from the server
- Request-Response:  the client sends one message and receives one back from the server
- Request-Stream:  a client sends one message and expects a stream of messages in response from the server.
- Channel: the client and server send streams of messages to each other.


## request -response 
- install Scoop
- ```powershell
    Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
    Invoke-RestMethod -Uri https://get.scoop.sh | Invoke-Expression
    ```

- scoop bucket add making https://github.com/making/scoop-bucket.git
- scoop update
- scoop install rsc

-  rsc --debug --request --data '{"courseName": "Spring"}' --route request-response --stacktrace tcp://localhost:7000