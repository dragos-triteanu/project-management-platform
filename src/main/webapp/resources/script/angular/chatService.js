(function(angular, SockJS, Stomp, _, undefined) {
    angular.module("chatApp.services").service("ChatService", function($q, $timeout) {

        var service = {}, listener = $q.defer(), socket = {
            client: null,
            stomp: null
        },orderId = $("#orderId").val(),
          from =   $("#userId").val();

        service.RECONNECT_TIMEOUT = 30000;
        service.SOCKET_URL = "/chat";
        service.CHAT_TOPIC = "/topic/message/" + orderId;
        service.CHAT_BROKER = "/app/chat";

        service.receive = function() {
            return listener.promise;
        };

        service.send = function(message) {
            socket.stomp.send(service.CHAT_BROKER, {
                priority: 9
            }, JSON.stringify({
                orderId:orderId,
                from:from,
                content: message
            }));
        };

        var reconnect = function() {
            $timeout(function() {
                initialize();
            }, this.RECONNECT_TIMEOUT);
        };

        var getMessage = function(data) {
            var message = JSON.parse(data), out = {};
            out.content = message.content;
            out.time = new Date(message.date);
            out.from  = message.from;
            out.orderId = message.orderId;
            return out;
        };

        var startListener = function() {
            socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
                listener.notify(getMessage(data.body));
            });
        };

        var initialize = function() {
            socket.client = new SockJS(service.SOCKET_URL);
            socket.stomp = Stomp.over(socket.client);
            socket.stomp.connect({}, startListener);
            socket.stomp.onclose = reconnect;
        };

        initialize();
        return service;
    });
})(angular, SockJS, Stomp, _);