import { useEffect, useState } from "react";
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/categories";

export default function AllCategories({onClickBackBtn}) {

    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const config = getConfig();

        axios.get(API_URL, config)
            .then((res) => {
                setCategories(res.data)
            }).catch(err => {
                window.alert("Something went wrong." + " " + err.message);
            })
    }, [])

    const categoriesListDisplay = categories.map((cat) => {
        return(
            <ul style={{marginLeft:"1rem", paddingRight:"5px", border: "2px solid blue", borderRadius:"5px"}} key={cat.id}>
                <li>ID: {cat.id}</li>
                <li>Category name: {cat.categoryName}</li>
                <li>Symbol filename: {cat.symbol}</li>
            </ul>
        )
    })

    return (
    <div style={{maxWidth:"100%"}}>
        <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
        <p>All Categories</p>
        <div style={{display:"flex", flexDirection:"row", maxWidth: "100%", flexWrap:"wrap" }}>
            {categoriesListDisplay}
        </div>
    </div>
    )
}