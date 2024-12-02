package util;

import java.util.ArrayList;

import model.ShineLessonModel;

public class ShineLessonMock {

    public static ArrayList<ShineLessonModel> getMockShineLessons() {
        ArrayList<ShineLessonModel> mockShineLessonModel = new ArrayList<>();

        // Adicionando 8 ShineLessons diferentes à lista
        mockShineLessonModel.add(new ShineLessonModel("Aula de Yoga", "R$ 50,00", "https://nosmulheresdaperiferia.com.br/wp-content/uploads/2020/05/professora-yoga-periferia_3.png", 3));
        mockShineLessonModel.add(new ShineLessonModel("Aula de Violão para Iniciantes", "R$ 90,00", "https://static.wixstatic.com/media/125e18_18a53602b1054e458de82a35cd579a05~mv2_d_3264_2448_s_4_2.jpg/v1/fill/w_980,h_735,al_c,q_85,usm_0.66_1.00_0.01,enc_avif,quality_auto/125e18_18a53602b1054e458de82a35cd579a05~mv2_d_3264_2448_s", 7));
        mockShineLessonModel.add(new ShineLessonModel("Conversação em Chinês", "R$ 70,00", "https://www.estudarfora.org.br/wp-content/uploads/2021/07/Mandarim.jpg.webp", 11));
        mockShineLessonModel.add(new ShineLessonModel("Consultoria Masculina de Estilo e Moda", "R$ 110,00", "https://www.savoa.com.br/wp-content/uploads/2018/07/curso-personal-stlist-so-para-homens.png", 5));
        mockShineLessonModel.add(new ShineLessonModel("Introdução à Fotografia", "R$ 120,00", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR71bolbzPAPN8032xkOZ-B-bLr0iOke2hsaw&s", 4));
        mockShineLessonModel.add(new ShineLessonModel("Pilates para Crianças", "R$ 60,00", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQp60JEbLGvNRmuW_pCGABy5lZ7hP4p8jvT1w&s", 2));
        mockShineLessonModel.add(new ShineLessonModel("Workshop de Desenho Artístico", "R$ 80,00", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWV7VtWsQ31VrgJLzr5ImZhLo6OlmBrnfT-Q&s", 9));
        mockShineLessonModel.add(new ShineLessonModel("Doces Caseiros: Receitas Fáceis", "R$ 100,00", "https://s2.glbimg.com/kR2oEqc6GOUP2Kvmt1UBYMPzXoI=/620x345/e.glbimg.com/og/ed/f/original/2022/08/25/boleira_fernanda_yamao_1.jpg", 8));
        mockShineLessonModel.add(new ShineLessonModel("Meditação contra Ansiedade", "R$ 10,00", "https://img.freepik.com/fotos-gratis/mulher-de-alto-angulo-meditando-em-casa_23-2150404951.jpg", 1));

        return mockShineLessonModel;
    }
}
