package com.provider.tv.use_cases

import com.provider.tv.data.ShowDataSource

class GetShowList(val showDataSource: ShowDataSource) {
    operator fun invoke() =
            showDataSource.getShowList()
}