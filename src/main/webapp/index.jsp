<html>
  <head>
    <script type="text/javascript">
      function connect() {
        var ws = new WebSocket("ws://localhost:8080/payara-websocket/websocket");
        ws.onopen = function() {
          console.log("connect");
        }
        ws.onclose = function(data) {
          console.log(data);
          if (data.code == 1000) {
            setTimeout(connect, 100);
          }
        }
      }

      setTimeout(connect, 2000);

      </script>
  </head>
<body>
<h2>Hello World!</h2>
</body>
</html>
