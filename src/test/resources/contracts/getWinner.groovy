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
            [min: [
                producer: "Joel Silver",
                interval: 1,
                previousWin: 1990,
                followingWin: 1991
            ],
                max: [
                    producer: "Matthew Vaughn",
                    interval: 13,
                    previousWin: 2002,
                    followingWin: 2015
                ]]
        )
    }
}