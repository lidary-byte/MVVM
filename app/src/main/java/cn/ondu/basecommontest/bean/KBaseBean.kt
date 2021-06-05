package cn.ondu.basecommontest.bean

import cn.ondu.basecommon.http.IBaseBean
import cn.ondu.basecommontest.Config

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: 正常情况下的baseBean 部分接口不需要
 */
class KBaseBean<T>(val status: Int, val message: String,val data: T) : IBaseBean<T> {
    override fun isSuccess(): Boolean = status == Config.HTTP_SUCCESS_CODE
    override fun errorCode(): Int = status
    override fun errorMsg(): String = message
    override fun data(): T = data
}
