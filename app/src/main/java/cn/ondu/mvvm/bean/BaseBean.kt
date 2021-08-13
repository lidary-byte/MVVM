package cn.ondu.mvvm.bean

import cn.ondu.basecommon.http.IBaseBean

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: 正常情况下的baseBean 部分接口不需要
 */

data class BaseBean<T>(val errorCode: Int, val errorMsg: String, val data: T) : IBaseBean<T> {
    override fun errorCode(): Int = errorCode
    override fun errorMsg(): String = errorMsg
    override fun data(): T = data
}

