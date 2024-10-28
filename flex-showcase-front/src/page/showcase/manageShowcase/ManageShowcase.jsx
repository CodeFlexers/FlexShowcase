import { useDispatch, useSelector } from "react-redux";
import api from "../../../common/api";
import MenuCard from "../../../component/MenuCard";
import { useEffect } from "react";
import { getUser } from "../../../reducer/UserDataSlice";


const ManageShowcase = () => {

    const dispatch = useDispatch();
    const {data, state, error} = useSelector((state) => state.user);

    useEffect(() => {
        if(!data){
            dispatch(getUser());
        }
        
    }, []);

    const getShowcaseData = async() => {

        const res = api.get('/portfolio?userCode');
    }

    return (
        <div>

            <MenuCard />
        
        </div>
    )

}

export default ManageShowcase;