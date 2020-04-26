namespace java xiong.user

/*
  消息服务接口
*/
service UserService  {
 
  void ping(),

  // 发送手机短信 
  string login(1:string username, 2:string password),

  // 使用邮箱注册
  string signup(1:string username, 2:string email, 3:string phone, 4:string password),
}