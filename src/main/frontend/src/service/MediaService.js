import axios from 'axios'

const BASE_URL = '/api/v1/';

class MediaService {

    fetchAll() {
        return axios.get(BASE_URL + 'decks');
    }

    fetcDeckById(id) {
        return axios.get(BASE_URL + 'deck/' + id);
    }

    delete(id) {
        return axios.delete(BASE_URL + 'deck/' + id);
    }

    addDeck(deck) {
        return axios.post("" + BASE_URL + 'deck', deck);
    }

     editDeck(deck) {
        return axios.put(BASE_URL + 'deck', deck);
    }
    fetchItems(id) {
        return axios.get(BASE_URL + "/elements/deck/" + id)
    }
    async upload(formData) {
        return await axios.post(BASE_URL + "/upload", formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                'Accept': '*/*',
            }
        });
    }
    async  addElements(element) {
        let response= await  axios.post(BASE_URL + 'element', element);
        window.location.reload();
    }
    deleteElement(id) {
        return axios.delete(BASE_URL + 'element/' + id);
    }
    async uploadFilesAndCreateElement( imgs ,element ){
        let promises = [];
        let urls = []
        for (var i = 0; i < imgs.length; i++) {
            const file = imgs[i]
            var formData = new FormData()
            formData.append("file", file)
            promises.push(await axios.post(BASE_URL + "/upload", formData,
                {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'Accept': '*/*',
                    }
                }).then(response => {
                    console.log(response.data)
                    urls.push(response.data)
                })
            )
        }

        Promise.all(promises).then(() => {
            element.mediaURLs=urls
            return this.addElements(element)

        });

    }


}



export default new MediaService();