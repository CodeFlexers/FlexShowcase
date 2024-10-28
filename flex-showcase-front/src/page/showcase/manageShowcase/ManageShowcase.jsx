import { useDispatch, useSelector } from "react-redux";
import api from "../../../common/api";
import MenuCard from "../../../component/MenuCard";
import { useEffect, useState } from "react";
import { getUser } from "../../../reducer/UserDataSlice";

import s from './ManageShowcase.module.css'


const ManageShowcase = () => {

    const dispatch = useDispatch();
    const {data, state, error} = useSelector((state) => state.user);
    const [showcase, setShowcase] = useState([]);

    useEffect(() => {
        if(!data){
            dispatch(getUser());
        }

    }, []);

    useEffect(() => {
        if(data){
            getShowcaseData();

        }

    }, [data])

    const getShowcaseData = async() => {

        const res = await api.get(`/portfolio?userCode=${data.userCode}`);
        setShowcase(res.data);

    }

    return (
        <div className={s.manageShowcase}>

            {showcase.map((s, idx) => 
                 <MenuCard title={s.projectName} link={s.websiteUrl} className="menuclass" width={'50%'} route={`/create-myShowcase/${s.portfolioCode}`} routeData={s}/>
            )}
           
        </div>
    )

}

export default ManageShowcase;