import axios from "axios";
import Cookies from "js-cookie";

export function upload(file) {
    let param = new FormData();
    param.append('id', 1);
    param.append('file', file);

    let token = Cookies.get("accessToken");
    let instance = axios.create({
        headers: {"Content-Type": "multipart/form-data", Authorization: token}
    })

    return new Promise((resolve, reject) => {
        instance.post("/upload/yun", param)
            .then(res => {
                resolve(res.data)
            })
            .catch((error) => {
                reject(error)
            })
    })
}
