package cn.ondu.basecommon.http

interface RetrofitInterface {

    fun baseUrl(): String {
        if (isDebug()) {
            return debugHost() + apiRelativePath()
        }
        return releaseHost() + apiRelativePath()
    }

    /**
     * 正式版Url
     */
    fun releaseHost(): String

    /**
     * 测试版Url
     */
    fun debugHost(): String?


    fun apiRelativePath(): String?

    fun isPrintLog(): Boolean

    fun isDebug(): Boolean

}