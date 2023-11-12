import React from "react";
import { MainCarouselData } from "./MainCarouselData";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";

const MainCarousel = () => {
    const items = MainCarouselData.map((item) => (
        <img
            src={item.image}
            className="cursor-pointer"
            role="presentation"
            alt=""
        />
    ));
    return (
        <AliceCarousel
            items={items}
            disableButtonsControls
            autoPlay
            autoPlayInterval={1000}
            infinite
        />
    );
};

export default MainCarousel;
