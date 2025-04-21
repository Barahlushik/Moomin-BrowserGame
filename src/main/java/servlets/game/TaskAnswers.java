package servlets.game;

enum TaskAnswers {

    BAG("""
            Этот огромный на вид паралипипед плавает среди моря и не внушает
            доверия и желания вытащить его из воды, пока не видешь в нем ручку
            """, "САКВОЯЖ"),

    FRENCHPRESS("""
            Издалека очень похожа на колбу с ручкой и чашкой внутри. Муми-мама заваривает в ней чай. 
            """, "ФРЕНЧПРЕСС"),

    WHISK("""
            Муми-мама часто взбивала им крем для торта. Очень странная вещь по форме, но имеющая милое название.
             Помоги мне вспомнить!
            """, "ВЕНЧИК");

    private String task;
    private String answer;
    private TaskAnswers(String task, String answer) {
        this.task = task;
        this.answer = answer;
    }

    public String getTask() {
        return task;
    }

    public String getAnswer() {
        return answer;
    }


}
