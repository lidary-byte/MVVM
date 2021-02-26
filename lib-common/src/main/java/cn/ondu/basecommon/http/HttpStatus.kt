package cn.ondu.basecommon.http

/**
 * @author: lcc
 * @date: 2021/2/26
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: http加载状态
 */
open class HttpStatus

//加载中
class LoadStatus : HttpStatus()

//加载成功
class SuccessStatus : HttpStatus()

//加载失败
class ErrorStatus(val errorMsg: String) : HttpStatus()

//加载完成
class FinishStatus : HttpStatus()
