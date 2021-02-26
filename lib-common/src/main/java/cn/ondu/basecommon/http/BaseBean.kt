package cn.ondu.basecommon.http

/**
 * @author: lcc
 * @date: 2021/2/26
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
open class BaseBean<T>(val errorCode: Int, val errorMsg: String, val data: T) {
   open fun isSuccess(): Boolean = errorCode == 0
}