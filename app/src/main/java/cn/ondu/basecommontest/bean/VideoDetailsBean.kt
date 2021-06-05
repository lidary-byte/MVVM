package cn.ondu.basecommontest.bean

data class VideoDetailsBean(
    var actors: String?,
    var count_series: Int,
    var country: String?,
    var cover: String?,
    var created_at: String?,
    var `data`: List<Data>,
    var data_source: List<DataSource>?,
    var hits: Any?,
    var id: Int?,
    var introduction: String?,
    var is_neihan: Int?,
    var lang: String?,
    var miner: Any?,
    var name: String?,
    var nunu_source: Any?,
    var producer: String?,
    var rank: Int?,
    var region: String?,
    var score: Any?,
    var source: String?,
    var source_key: String?,
    var status: Int?,
    var style: Any?,
    var subname: String?,
    var tags: Any?,
    var type: Any?,
    var type_name: String?,
    var updated_at: String?,
    var year: String?
)



data class DataSource(
    var name: String?,
    var url: String?
)