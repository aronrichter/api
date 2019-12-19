package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "get films"

    request {
        url("/films")
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body(
            [[
                producer: "Producer 1",
                interval: 1,
                previousWin: 2018,
                followingWin: 2019
            ],
                [
                    producer: "Producer 2",
                    interval: 2,
                    previousWin: 2017,
                    followingWin: 2019
                ]]
        )
    }
}