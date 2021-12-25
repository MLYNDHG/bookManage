import request from '@/utils/request'

/*
 *
 */
export function selectSourcelist() {
    return request({
        url: request.baseUrl + `/selectSourcelist`,
        method: 'GET',
        params: {

        }
    })
}