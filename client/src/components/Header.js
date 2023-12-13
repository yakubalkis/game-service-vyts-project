import { useEffect, useState } from "react"
import Logout from "./Logout"


export default function Header(){

    const [isLoggedIn, setIsLoggedIn] = useState(false)
    const username = localStorage.getItem("usernameOfCurrentUser"); // get host ID no from local storage
    

    useEffect(() => {
        if(username){ // if host id does exist, we know that user logged in
            setIsLoggedIn(true) // set true to render Logout component
        }
    }, []);
    
    return(
        <div>
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div style={{ marginLeft: "1rem"}} className="navbar-brand">Game Service Client App</div>
                    {isLoggedIn && <Logout/>}
                </nav>
            </header>
        </div>
    )

}