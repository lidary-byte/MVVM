package cn.ondu.basecommon


object BaseCommon {
    internal var debug: Boolean = false
    internal var tag = BaseCommon::class.java.simpleName

    fun init(debug: Boolean, logTag: String = BaseCommon::class.java.simpleName) {
        this.debug = debug
        this.tag = logTag
    }


}