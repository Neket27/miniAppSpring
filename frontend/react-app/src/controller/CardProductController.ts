import {makeAutoObservable} from "mobx";

export default class CardProductController{

    constructor() {
        makeAutoObservable(this);
    }

}