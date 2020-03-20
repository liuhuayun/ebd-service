#language: zh-CN

@StatsUserLogin_crud  @test_now
功能: 操作用户登录信息

@StatsUserLogin_list
场景: 获取登录用户信息
假如 我想获取当前登录系统所有用户信息
当 我发送获取当前登录系统所有用户的请求时
那么我应该是可以收到"SUCCESS"

@StatsUserLogin_add
场景: 添加登录用户信息
假如 user_id为"08fae03901f511e99b870242ac110003"的用户即将通过"WEB"登录系统
当 该用户发送登录的请求时
那么我应该是可以收到"SUCCESS"

@StatsUserLogin_update
场景: 修改登录用户信息
假如 上面的用户将登录系统的方式改为"IOS"
当 该用户再次发送登录的请求时
那么我应该是可以收到"SUCCESS"

@StatsUserLogin_detail
场景: 通过user_id获取登录用户信息
假如 我需要获取到上面的用户的登录信息
当 我发送的请求时
那么我应该是可以收到"SUCCESS"	

@StatsUserLogin_delete
场景: 删除登录用户信息
假如 我需要把上面的用户登录信息删除
当 该我发送删除的请求时
那么我应该是可以收到"SUCCESS"

@StatsUserLogin_batchadd
场景: 批量添加登录用户信息
假如 我需要一次性添加"5"个用户的登录信息时
当 我发送批量添加的请求时
那么我应该是可以收到"SUCCESS"

@StatsUserLogin_summary
场景: 查看某段时间内用户的登录信息
假如 我需要查看"2019-01-04"至"2019-01-06"的登录用户信息
当 我发送查看某个时间段的用户登录信息的请求时
那么我应该是可以收到"SUCCESS"

@StatsUserLogin_total
场景: 统计活跃用户数
假如 我需要查看本周用户活跃数,此时需指定"week"
当 我发送查看本周活跃用户数的请求时
那么我应该是可以收到"SUCCESS"
