### 授权码模式
1. 获取授权码
浏览器请求：
> http://localhost:8080/oauth/?client_id=client-id&redirect_uri=http://localhost:9001/callback&response_type=code&scope=read_userinfo

2. 获取访问令牌
> curl -X POST --user client-id:112233 http://localhost:8080/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "code=8uYpdo&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalhost%3A9001%2Fcallback&scope=read_userinfo"

3. 调用API
> curl -X GET http://localhost:8080/api/userinfo -H "authorization: Bearer 36cded80-b6f5-43b7-bdfc-594788a24530"

### 简化模式
1. 获取访问令牌
浏览器请求：
> http://localhost:8080/oauth/authorize?client_id=clientapp&redirect_uri=http://localhost:9001/callback&response_type=token&scope=read_userinfo&state=abc
响应案例：http://localhost:9001/callback#access_token=0406040a-779e-4b5e-adf1-bf2f02031e83&token_type=bearer&state=abc&expires_in=119

2. 调用API
> curl -X GET http://localhost:8080/api/userinfo -H "authorization: Bearer 0406040a-779e-4b5e-adf1-bf2f02031e83"