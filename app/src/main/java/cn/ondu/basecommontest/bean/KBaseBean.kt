package cn.ondu.basecommontest.bean

import cn.ondu.basecommon.http.BaseBean

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description: 正常情况下的baseBean 部分接口不需要
 */
class KBaseBean<T>(val status: Int, val error: String, data: T) : BaseBean<T>(status, error, data) {
    override fun isSuccess(): Boolean = status == 1
}