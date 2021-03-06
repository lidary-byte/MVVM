package cn.ondu.basecommontest.bean

/**
 * @author: lcc
 * @date: 2021/3/7
 * @GitHub:
 * @email：lidaryl@163.com
 * @description:
 */
data class MusicListBean(
    val JS_CSS_DATE: Int?,
    val __Tpl: String?,
    val fr: Any?,
    val info: Info?,
    val kg_domain: String?,
    val list: ListY?,
    val pagesize: Int?,
    val src: String?,
    val ver: String?
)

data class Info(
    val list: Any?
)

data class ListY(
    val list: ListX?,
    val page: Int?,
    val pagesize: Int?
)

data class ListX(
    val info: List<InfoX>?,
    val timestamp: Int?,
    val total: Int?
)

data class InfoX(
    val `320filesize`: Int?,
    val `320hash`: String?,
    val `320privilege`: Int?,
    val album_audio_id: Int?,
    val album_id: String?,
    val audio_id: Int?,
    val bitrate: Int?,
    val brief: String?,
    val duration: Int?,
    val extname: String?,
    val fail_process: Int?,
    val fail_process_320: Int?,
    val fail_process_sq: Int?,
    val feetype: Int?,
    val filename: String?,
    val filesize: Int?,
    val has_accompany: Int?,
    val hash: String?,
    val inlist: Int?,
    val m4afilesize: Int?,
    val mvhash: String?,
    val old_cpy: Int?,
    val pay_type: Int?,
    val pay_type_320: Int?,
    val pay_type_sq: Int?,
    val pkg_price: Int?,
    val pkg_price_320: Int?,
    val pkg_price_sq: Int?,
    val price: Int?,
    val price_320: Int?,
    val price_sq: Int?,
    val privilege: Int?,
    val remark: String?,
    val rp_publish: Int?,
    val rp_type: String?,
    val sqfilesize: Int?,
    val sqhash: String?,
    val sqprivilege: Int?,
    val topic_url: String?,
    val topic_url_320: String?,
    val topic_url_sq: String?,
    val trans_param: TransParam?
)

data class TransParam(
    val appid_block: String?,
    val cid: Int?,
    val cpy_attr0: Int?,
    val cpy_grade: Int?,
    val cpy_level: Int?,
    val display: Int?,
    val display_rate: Int?,
    val hash_offset: HashOffset?,
    val musicpack_advance: Int?,
    val pay_block_tpl: Int?
)

data class HashOffset(
    val end_byte: Int?,
    val end_ms: Int?,
    val file_type: Int?,
    val offset_hash: String?,
    val start_byte: Int?,
    val start_ms: Int?
)