package cn.ondu.basecommon.http

/**
 * @author: lcc
 * @date: 2021/2/26
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
interface IBaseBean<T> {
    fun errorCode(): Int
    fun errorMsg(): String
    fun data(): T
    fun isSuccess(): Boolean = errorCode() == 0
}