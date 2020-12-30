package com.provider.tv.use_cases

import com.provider.tv.data.ShowDataSource

class GetShowList(val showDataSource: ShowDataSource) {
    operator fun invoke(borderId: Int) =
            showDataSource.getShowList(borderId)
}