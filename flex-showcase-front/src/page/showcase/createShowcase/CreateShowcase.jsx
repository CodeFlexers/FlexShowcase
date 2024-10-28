import { useState } from 'react';
import api from '../../../common/api';
import s from './CreateShowcase.module.css';


const CreateShowcase = () => {

    const [showcaseData, setShowcaseData] = useState({
        projectName: "",
        websiteUrl: "",
        descriptionHTML: "",
        thumbnailImage: "",
        githubRepo: "",

    });


    /* input handler */
    const handlerInput = (e) => {
        const {name, value} = e.target;
        setShowcaseData((prev) => ({    // 함수를 전달하면 현재 상태를 인수로, 반환하는 객체를 새로운 상태로 사용
            ...prev,
            [name]: value,  // [동적으로 계산된 속성 이름]

        }));
        console.log("showcaseData: ", showcaseData);
        
    }


    /* 쇼케이스 등록 요청*/
    const registShowcase = async() => {

        const res = await api.put('/portfolio', showcaseData);
        console.log(res);
        

    }



    return (
        <div className={s.CreateShowcase}>
            
            <div className={s.formContainer}>
            <div className={s.title}>쇼케이스 작성하기</div>

                <label htmlFor="title" className={s.subTitle}>제목</label>
                <input type="text" id="title" 
                name="projectName" placeholder="쇼케이스 제목" 
                value={showcaseData.projectName}
                onChange={handlerInput}
                />

                <label htmlFor="portfolio" className={s.subTitle}>쇼케이스 URL</label>
                <input type="url" id="portfolio" 
                name="websiteUrl" placeholder="프로젝트, 웹사이트 링크 url"
                value={showcaseData.websiteUrl}
                onChange={handlerInput}
                />

                <label htmlFor="contentHtml" className={s.subTitle}>내용</label>
                <textarea id="contentHtml" name="descriptionHTML" 
                placeholder="쇼케이스 내용을 자유롭게 작성해주세요. 예시)
                스킬: JavaScript, React, Spring Boot...
                설명: 할 일 서비스
                기능: 할 일 추가, 수정, 삭제, 카테고리 필터링.."
                value={showcaseData.descriptionHTML}
                onChange={handlerInput}
                
                ></textarea>

                <label htmlFor="thumbnail" className={s.subTitle}>이미지</label>
                <input type="file" id="thumbnail" 
                name="thumbnailImage" multiple
                value={showcaseData.thumbnailImage}
                onChange={handlerInput}
                />

                <label htmlFor="github" className={s.subTitle}>깃허브</label>
                <input type="url" id="github"
                name="githubRepo" placeholder="Github 주소가 있으면 첨부해주세요."
                value={showcaseData.githubRepo}
                onChange={handlerInput}
                />

                <button onClick={() => registShowcase()}>등록하기</button>

            </div>

            
        </div>


    )
}

export default CreateShowcase;