
// @ts-ignore
import ReactStars from 'react-rating-stars-component';
import {FC} from "react";
export interface IRatingProps{
    activeColor:string;
    value:number;
    count:number;
    size:number;
    onChange:(newRating:number)=>void;
}

export const Rating:FC<IRatingProps> =({activeColor,value,count,size,onChange})=>{
    return (
        <ReactStars
            count={count}
            value={value}
            onChange={onChange}
            size={size}
            activeColor={activeColor}
        />
    );

}