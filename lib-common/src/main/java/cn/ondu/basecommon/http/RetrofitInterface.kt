package cn.ondu.basecommon.http

interface RetrofitInterface {

    fun baseUrl(): String {
        if (isDebug()) {
            return debugHost()
        }
        return releaseHost()
    }

    /**
     * 正式版Url
     */
    fun releaseHost(): String

    /**
     * 测试版Url
     */
    fun debugHost(): String

    /**
     * 是否打印日志
     */
    fun isPrintLog(): Boolean

    /**
     * 是否是debug
     */
    fun isDebug(): Boolean

}