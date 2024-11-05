import FormForDetailProduct from "../../../form";
import {IDetailProduct} from "../../../../../model/product/IDetailProduct";
import {IFeedback} from "../../../../../model/rating/IFeedback";
import {useContext, useEffect, useState} from "react";
import {ContextService, State} from "../../../../../main";
import {Rating} from "../../../../rating/Rating";
import {inRange} from "transliteration/dist/node/src/common/utils";
import {FeedbackResponse} from "../../../../../model/response/rating/FeedbackResponse";

const Review =(props: {productDetail:IDetailProduct|undefined})=>{
    const context:State = useContext(ContextService);
    const [feedbackList,setFeedbackList] = useState<Array<FeedbackResponse>>([]);
    
    const getFeedbackList =  async ()=>{
        const feedbacks =  await context.ratingService.getFeedbackList(props.productDetail.id);
        setFeedbackList(feedbacks);
    }
    
    useEffect(() => {
        getFeedbackList();
    },[])
    

    const feedbacks = feedbackList.map(feedback=> {
        let rating = Array.from({ length: feedback.evaluation }, (_, i) => (
            <i key={i} className="fas fa-star"></i>
        ));

        const date:Date = new Date(feedback.date);
        return (
            <div className="ant107_shop-single-review">
                <div className="ant107_shop-reviewer-img">
                    <img src="/img/ant107_shop/avatar2.png" alt=""/>
                </div>
                <div className="ant107_shop-reviewer">
                    <h6>{feedback.nameUser}</h6>
                    <p>{feedback.message}</p>
                </div>
                <div className="ant107_shop-reviewer-rating">
                    <span>{date.getDate() +" "+(date.getUTCMonth()+1)+" "+date.getFullYear()}</span>
                    <div className="ant107_shop-ratings">
                        {rating}
                    </div>
                    {feedback.imageList && feedback.imageList.map(image =>
                        <img width='70px' height='70px' src={"data:image/png;base64," +image}/>
                    )}

                </div>
            </div>
        );
    });

    return (
        <div className="tab-pane" id="ant107_shop-review">

            {feedbacks}

            <div className="ant107_shop-review-form mt-5">
                <div className="mb-4 text-center">
                    <h2>Написать отзыв</h2>
                </div>
                {// @ts-ignore
                <FormForDetailProduct idProduct={props.productDetail?.id}/>
                }
            </div>
        </div>
    );
}

export default Review;