namespace java xiong.message

/*
  消息服务接口
*/
service MessageService  {
 
  void ping(),

  // 发送手机短信 
  bool sendPhoneMsg(1:string phone, 2:string msg),

  // 发送邮件信息
  bool sendEmailMsg(1:string email, 2:string msg)
}