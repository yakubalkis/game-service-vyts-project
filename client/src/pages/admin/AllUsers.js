import { useEffect, useState } from "react"
import getConfig from "../../utils/getConfig"
import axios from "axios";

const API_URL = "http://localhost:8080/api/users";

export default function AllUsers({onClickBackBtn}) {
    const [userList, setUserList] = useState([]);

    useEffect(() => {
        const config = getConfig();

        axios.get(API_URL, config)
            .then((res) => {
                setUserList(res.data)
            }).catch(err => {
                window.alert("Something went wrong." + " " + err.message);
            })
    }, []);

    const userListDisplay = userList.map((user) => {
        return (
            <ul style={{marginLeft:"1rem", paddingRight:"5px" ,border: "2px solid blue", borderRadius:"5px"}} key={user.id}>
                <li>ID: {user.id}</li>
                <li>Role: {user.role}</li>
                <li>Email: {user.email}</li>
                <li>Username: {user.username}</li>
                <li>Phone number: {user.phoneNumber}</li>
                <li>Date of join: {user.dateOfJoin}</li>
                <li>Current Game Money: {user.currentGameMoney}</li>
                <li>Level point: {user.level}</li>
                <li>Level name: {user.levelName}</li>
                <li>Rank point: {user.rank}</li>
                <li>Rank name: {user.rankName}</li>
            </ul>)
    })

    return(
        <div style={{maxWidth:"100%"}}>
            <button onClick={() => onClickBackBtn()}> {"<-- Back"}</button>
            <p>All Users</p>
            <div style={{display:"flex", flexDirection:"row", maxWidth: "100%", flexWrap:"wrap" }}>
                {userListDisplay}
            </div>
        </div>
    )
}