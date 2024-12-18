import axios from "axios";
import cities from '../../../react-app/src/russian-cities.json'
import {string} from "prop-types";

export class LocationDetector {

    constructor() {
        this.getLocation()
    }

    private city:string;

    public async getCity()
    {
       await this.getLocation()
        return this.city;
    }

    public setCity(city: string) {
        this.city = city;
    }

    private compareCoordinates(coords1, coords2, epsilon ) {
        // console.log(coords1, coords2, epsilon);
        const isLatClose = Math.abs(parseFloat(coords1.lat) - parseFloat(coords2.lat)) <= epsilon;
        const isLonClose = Math.abs(parseFloat(coords1.lon) - parseFloat(coords2.lon)) <= epsilon;
     //   console.log(isLatClose +" "+isLonClose);
        return isLatClose && isLonClose;
    }


    private async getLocation() {
        try {
            const response = await axios.get('/get-location').then(r=>r.data);
            if (response) {
                const lat = response.latitude;
                const lon = response.longitude;
                console.log(lat +"  "+lon)

                const ipCoords = {
                    lat: response.latitude,
                    lon:  response.longitude
                };
            cities.map(city =>{
                // console.log("c"+city.name)
                const localCoords = {
                    lat: city.coords.lat,
                    lon: city.coords.lon
                };
                if (this.compareCoordinates(localCoords, ipCoords, 0.01)) {
                    this.city = city.name;
                    console.log("c= "+city.name)
                }
                });


            } else {
                this.city='Неопределён'
                console.log("Не удалось получить данные о городе");
            }
        } catch (error) {
            console.error("Ошибка при получении данных о городе:", error);
        }
    }
}

