namespace java xiong.user

/*
  消息服务接口
*/
service MessageService  {
 
  void ping(),

  // 发送手机短信 
  bool login(1:string username, 2:string password),

  // 使用邮箱注册
  i64 signup(1:string username, 2:string password),
}