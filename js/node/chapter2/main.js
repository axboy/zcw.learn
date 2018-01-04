let events = require("events");

let eventEmitter = new events.EventEmitter();

//绑定helloEvent事件到处理程序
eventEmitter.on("helloEvent",()=>{console.log("hello event");});
//触发helloEvent事件
eventEmitter.emit('helloEvent');

//测试带参数的事件处理
eventEmitter.on("eventWithParams",(param1, param2)=>{console.log(`eventWithParams:${param1},${param2}`);});
eventEmitter.emit('eventWithParams', "p1", "p2");
