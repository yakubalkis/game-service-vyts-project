import { useState } from "react";
import UserDetails from "./UserDetails";
import AddSubGameMoney from "./AddSubGameMoney";
import AllItems from "../admin/AllItems";
import BuyAnItem from "./BuyAnItem";
import UserInventory from "./UserInventory";
import AllPurchases from "../admin/AllPurchases";

export default function UserHomePage() {
    const [renderOption, setRenderOption] = useState("");

    function handleClick(componentName) {
        setRenderOption(state => componentName);
    }

    function onClickBackBtn() {
        setRenderOption(state => "");
    }

    return(
        <>
        <h2>User Panel</h2>
        <div style={{marginTop: "20px", display: "flex", flexWrap:"wrap"}}>

            {renderOption==="userDetails" &&  <UserDetails onClickBackBtn={onClickBackBtn} />}
            {renderOption==="addSubGameMoney" &&  <AddSubGameMoney onClickBackBtn={onClickBackBtn} />}
            {renderOption==="allItems" &&  <AllItems onClickBackBtn={onClickBackBtn} />}
            {renderOption==="buyItem" &&  <BuyAnItem onClickBackBtn={onClickBackBtn} />}
            {renderOption==="inventory" &&  <UserInventory onClickBackBtn={onClickBackBtn} />}
            {renderOption==="purchases" &&  <AllPurchases onClickBackBtn={onClickBackBtn} forWho={""} />}
            

            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("userDetails")}>
                                        Click to see your personal details
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("addSubGameMoney")}>
                                        Click to add/sub game money to your budget
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("allItems")}>
                                        Click to see all items
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("buyItem")}>
                                        Click to buy an item
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("inventory")}>
                                        Click to see your inventory
                </button>
            }
            {renderOption === "" && 
                <button style={{marginLeft: "10px",marginTop:"10px"}} className="btn btn-primary w-10 fs-6" onClick={() => handleClick("purchases")}>
                                        Click to see your purchases
                </button>
            }
        </div>
        </>
    )
}