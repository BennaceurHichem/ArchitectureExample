package com.example.android.architectureexample;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Database(entities = {Book.class}, version =1)
public abstract class BookDatabase extends RoomDatabase {


    private static BookDatabase instance;


// we can access to our databse by using DAO methods ..§//
    public abstract BookDao bookDAO();



        //synchronized means that only one thread at the time ca    n execute this method to avoid accidentally
    public static synchronized BookDatabase getInstance(Context context )
    {
        if( instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BookDatabase.class, "Book Databse")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        //By adding addCallback our databse will be created and populated with the creation
        }

            //when we increment the version number
        //AFTER CREATNG A DATABSE INSTANCE WE CAN FILL THE CONTENT
        // OF THE ABSTRACT METHOD bookDao() [ the creation is done with Room.databaseBuilder....


            return instance;
        }
    // in this case we can, after creating the database,
    // make an initial fill of the book_table by usingRoomDatabase.calBack()

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback()
    {

        // onCreate when the databse is created, we dn't need onOpen which is executed every db opening
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };


    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
    {

        private BookDao bookDao;
        List<Book> lstBook;
        HashMap descriptionMap;
        List<String> titles;

        public PopulateDbAsyncTask(BookDatabase bookDb) {
            // we can o that because the Db has already created
            this.bookDao = bookDb.bookDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            lstBook = new ArrayList<Book>();
            descriptionMap =  new HashMap<String,String>();
            titles = new ArrayList<String>();
            titles.add("عندما التقيت عمر بن الخطاب");
            titles.add("الم نشرح لك صدرك");
            titles.add("النبي صلى الله عليه وسلم");
            titles.add("العواصف");
            titles.add("وحي القلم");
            titles.add("عبقريات العقاد");
            titles.add("بروتوكولات حكماء صهيون");
            titles.add("قبس من حكايا");
            titles.add("هيبتا");
            titles.add("اغتصاب ولكن تحت سقف واحد");
            titles.add("لا تسألني لماذا أحببتها");


            descriptionMap.put("عندما التقيت عمر بن الخطاب","كان في السادسة والعشرين عندما أصابته دعوة النبي صلى الله عليه وسلم في قلبه : اللهم أعز الإسلام بأحب الرجلين إليك ، عمر بن الخطاب أو عمرو بن هشام ! هكذا بدأت الحكاية ، دعوة جذبته من ياقة كفره إلى نور الإسلام ، وانتشلته من مستنقع الرذيلة إلى قمة الفضيلة ، واستلته من دار الندوة إلى دار الأرقم ! ولأن الناس معادن خيارهم في الجاهلية خيارهم في الإسلام إذا فقهوا ، كان عمر الجاهلي مهيأ بإتقان ليكون عمر الفاروق ! كل ماينقصه إعادة هيكلة وصياغة، وليس أقدر من الإسلام على هيكلة الناس وصياغتهم من جديد ! فالإسلام لا يلغي الطبائع إنما يهذبها ، ولا يهدم الصفات وإنما يصقلها ، وفي الإسلام هذّب عمر وصفل حتى صار واحداً من الذين لا يأتون إلا مرة واحدة في التاريخ .");
            descriptionMap.put("الم نشرح لك صدرك","المسألة لم تتعلق يوماً بالأسبق وإنما بالأصدق وهذا كتاب يعرضُ حياة رجال جمعوا المجدين معاً : الأسبق والأصدق ولكن هذا الدين يفتح ذراعيه في كل عصر وينادي على الناس في كل مكان أن هلمُّوا إليَّ هناك دوما متسع مهما اكتظت الصفوف وهناك فرصة للحاق بالقافلة وإن كانت بدأت مسيرها منذ أربعة عشرة قرناً ثم من يدري فلعلَّ الفتن قد فشتْ في عصركَ لتنال أجرَ الثبات ! ");
            descriptionMap.put("النبي صلى الله عليه وسلم","حين يكتب «جبران» عن نبيِّه تتجسَّد القِيَم والمعاني الإنسانية التي تسمو بنفسها على أيِّ دينٍ أو عِرقٍ أو لون؛ إنها الإنسانية في أبهى صورها. لا شكَّ أن كتاب «النبي» هو دُرَّةُ ما كتبه «جبران خليل جبران»، وخلاصةُ ما توصَّلَ إليه، وعصارةُ تجارِبه الذاتية ونظرته الحياتية؛ فقد ضمَّنَه كلَّ آرائه في الحياةِ والموت، الطعامِ والشراب، الحبِّ والزواج، وغيرها؛ لذا فقد اعتبره جبران «ولادتَه الثانية» التي ظلَّ ينتظرها ألف عام. ويسرد جبران آراءَه على لسان الحكيم «المصطفى» الذي ظلَّ بعيدًا عن وطنه اثني عشر عامًا، وعاش بين سكان جزيرة «أورفاليس» كواحدٍ منهم، منتظرًا عودته إلى مسقط رأسه. وحينما ترسو السفينة ويحين موعدُ رحيله يرجوه سكانُ الجزيرة أن يخطب فيهم؛ فكانت خطبةُ الوداع التي لخَّصَ فيها مذهبه. لقد نجح جبران في كتابه في أن يتجاوز حدودَ ديانته، ليُرسيَ دعائمَ إنسانية تحترم الإنسانَ لكونه إنسانًا لا لأيِّ عاملٍ آخر.");
            descriptionMap.put("العواصف","يتحدث جبران في هذا الكتاب عن الحياة والموت والحب، بمنطقيةٍ فلسفية مُزْدانةً بديباجة أدبية؛ فهو يستنطق بفلسفته ألسنة الأزهار والأشجار لكي يُفصح من خلالها عمَّا في هذا الكون من أسرار. وقد قسَّم جبران في هذا الكتاب كلمة العبودية وفقًا لدور الأشخاص في تمثيل العبودية على مسرح الحياة الإنسانية، كما وصف حال الشرقيين وجسدهم في صورةٍ تُظْهِرُ خنوعهم وتخاذلهم، واصفًا الشرق بأنه المريض الذي تناوبته العلل وتداولته الأوبئة حتي تعوَّد السَّقمَ وأَلِفَ الألم، ويدعو جبران للخروج من هذه الحالة الحضارية من خلال تمجيده لقيم التمرد والتحلي بروح الفرادة بدل من الانسياق وراء التقاليد بشكل أعمى.");
            descriptionMap.put("وحي القلم","يرسم مصطفى صادق الرافعي كتابه هذا بريشة الفنان، ويُزيِّن معانيَه بحُلِيِّ البيان، ويلوِّنه بحسن الإيمان، فتتداخل الحدود بين العالم المادي وعالم الإنسان، فلا يدري القارئ أحقيقةٌ ما يقرؤه في هذا الكتاب أم خيال؟! أعقلٌ هو أم جنون؟! حقًّا، لقد أفاد الرافعي بما فاض به خاطرُه، وجاد به فكرُه، وسال به قلمه، فسطَّر مجموعةً رائعة من النثريات، تباينتْ بين فصولٍ ومقالاتٍ وقصص عن مواضيع متنوِّعة، كتبها في ظروفٍ مختلفة وأوقاتٍ متفاوتة، فأخرج لنا في النهاية تحفةً أدبيةً استحقَّتْ أن تُسمَّى بحقٍّ «وَحْي القَلَم».");
            descriptionMap.put("عبقريات العقاد","هي سلسلة من المؤلفات قام بتاليفها المفكر عباس محمود العقاد و تضم الخلفاء الراشدين و المسيح . العبقريات ليست سرداً للأحداث التاريخية أو لأحكام الإسلام إنما عبقرية النبي محمد و أبو بكر الصديق و عمر بن الخطاب و عثمان بن عفان و علي بن أبي طالب هي إظهار للعبقرية العربية التي أنجبت هذه الشخصيات و أن العبقرية ليست حكراً على أمة من الامم . كما يعارض الكاتب من يخطٌؤون أحد هؤلاء الصحابة حيث يقول أنهم كلهم عباقرة و لكن احتاجت لهم الأمة الإسلامية في ظرف معين من الظروف . كما يتحدث الكتاب عن عبقرية المسيح و يشمل الكتاب دراسة عن المسيحية والمسيح في التاريخ و عن الاناجيل . مع العلم يمكن أن تجد كتاب عبقرية المسيح باسم حياة المسيح و عبقرية عثمان باسم ذو النورين عثمان بن عفان .");
            descriptionMap.put("بروتوكولات حكماء صهيون","بروتوكولات حكماء صهيون أو قواعد حكماء صهيون هي وثيقة مزيفة،تتحدث عن خطة لغزو العالم اُنشِأت من قِبَل اليهود وهي تتضمن 24 بروتوكولاً. في عام 1901 كتَبَ هذه الوثيقة ماثيو جولوڤينسكى مُزَوِر ومُخبر من الشرطة السياسية القيصرية وكانت مُستوحاه من كتاب حوار في الجحيم بين ميكافيل ومونتسكيو للمؤلف موريس چولى الذي يُشير في كتابه إلى وجود خطة زائفة ومُسبقة لغزو العالم من قِبَل نابليون الثالث وقد تم تطويرها من مجلس حكماء اليهود بهدف تدمير المسيحية والهيمنة على العالم . يحتوى هذا الكتاب على عدّة تقارير تكشف خطة سرية للسيطرة على العالم، تعتمد هذه الخطة على العنف والحِيَل والحروب والثورات وترتَكز على التحديث الصناعي والرأسمالية لتثبيت السلطة اليهودية. في العشرينات من القرن الماضي واجهت ألمانيا صعوبات اقتصادية، مما جعل ادولف هتلر يربط بينها وبين مخطط اليهود للسيطرة على العالم والذي كان مستحوذاً على تفكيره آنذاك وقد تبين ذلك من خلال كتابه مين كامبف أو كفاحي الذي فسر فيه نظرية المؤامرة اليهودية من وجهة نظره فنَتَجَ عن ذلك المذابح التي تَعَرض لها اليهود في ألمانيا النازية والمعروفة باسم ليلة الكريستال وأصبح ذلك الكتاب في يومنا هذا رمزاً لمعاداة السامية. و قد أصدر الباحث اليهودى نورمان كوهين كتابه إنذار بالإبادة الذي يُوُضّح فيه أن أسطورة الهيمنة العالمية هي فكرة فرنسية ظهرت خلال القرن التاسع عشر ولم تتضمن أى إشارة تُدين اليهود.");
            descriptionMap.put("قبس من حكايا","تاريخنا ليس كأيِّ تاريخٍ سبقَ وأنْ قرأتَه، ولهذا اخترنا منه قبسًا، قبس فيه كلُّ شيءٍ، العزّةُ والمنعة والكبرياء، ولحظاتُ الخزيّ التي تؤلم، والوجع الذي يُبكي، والسخط الذي يجرح حنجرتك المسكينة من البكاء وهي تهتف أن ياللغباء! تستطيع أن تعيش فيه كلّ شيءٍ، ستبكي وتفرح وتحزن في صفحةٍ واحدةٍ لا غير، وهذا ليس بغريبٍ عن التاريخ. سترى بعينيك الغلام وهو يتوعد عمر بن الخطاب، ستسافر مع خالد من اليمن إلى أبعدِ نقطةٍ في الشمال ليحكيَ لكَ ما فعله بالفرس، وتعبر معه السماوة إلى جيوش المسلمين الأربعةِ في اليرموك ترى الرومان وهم غاطسون في الحديد، وتعود مرة أخرى للعراق ترى المثنى بفرسه يختال بين الجموع، ويحكي لكَ سعدُ بن أبي وقاص ما فعله طليحة بن خويلد بالفرس في القادسية....");
            descriptionMap.put("هيبتا","تأخذنا روايه ( هيبتا ) الي ذلك العالم الذي أهلكه الجميع بحثا .. ذلك العالم الذي رغم تكرار قصصه و رواياته الا أن الجميع فيه يقع في نفس الأخطاء , و يعيد نفس الأحداث , و يتألم نفس الألم ... خلال محاضرة مدتها ست ساعات يأخذنا \" أسامه \" المحاضر الي حالات نادره .. و رغم ندرتها لن تستطيع الا أن تجد نفسك فيها في عالم الحب و الامل و الألم .. من خلال حالات نعيشهم و نفهم منهم تلك المراحل السبع التي لخصت كل القواعد \"قواعد ال \"هـيـبـتـا ");
            descriptionMap.put("اغتصاب ولكن تحت سقف واحد","الحقد الغيرة الحب و الشك الثقة و العلاقة بربنا و نظرة المجتمع للبنت من أكثر من اتجاه رواية رومانسية ذات طابع ديني لا تندم على أحساس صادق قد بذلته فالطيور لا تأخذ مقابًلا على تغريدها .");
            descriptionMap.put("لا تسألني لماذا أحببتها","د. سعداوي من أبرع جراحي المخ والأعصاب بمصر، يدفعه الحب لإجراء تجربة طبية مخيفة، وتدفعه التجربة لخوض أهوال لم يحلم بمثقال ذرة منها في حياته، فهل سيفلح في النجاة من تلك الأهوال ؟ وهل تنجح التجربة ؟ أم يكون الألم هو البطل الذي لا غنى عنه ؟");

            Log.d("TAG", "onCreate: Size of title list is : "+titles.size());

            //lsBook.add(new Book("1980","Description of the Book ","Romans",R.drawable.2) );
            lstBook.add(new Book("عندما التقيت عمر بن الخطاب","أدهم شرقاوى",descriptionMap.get(titles.get(0)).toString(),R.drawable.aandma_altkyt_aamr_bn_alkhtab));
            lstBook.add(new Book("الم نشرح لك صدرك","فهد العيد",descriptionMap.get(titles.get(1)).toString(),R.drawable.alm_nshrh_lk_sdrk));
            lstBook.add(new Book("النبي صلى الله عليه وسلم ","جبران خليل جبران",descriptionMap.get(titles.get(2)).toString(),R.drawable.alnby));
            lstBook.add(new Book("العواصف","جبران خليل جبران",descriptionMap.get(titles.get(3)).toString(),R.drawable.alaaoasf));
            lstBook.add(new Book("وحي القلم","مصطفى صادق الرافعي",descriptionMap.get(titles.get(4)).toString(),R.drawable.ohy_alklm_ajza));
            lstBook.add(new Book("عبقريات العقاد","عباس محمود العقاد",descriptionMap.get(titles.get(5)).toString(),R.drawable.aabkryat_alaakad));
            lstBook.add(new Book("بروتوكولات حكماء صهيون","فيكتور مارسدن",descriptionMap.get(titles.get(6)).toString(),R.drawable.brotokolat_hkma_shyon));
            lstBook.add(new Book("قبس من حكايا","عمر محمد",descriptionMap.get(titles.get(7)).toString(),R.drawable.kbs_mn_hkaya));
            lstBook.add(new Book("هيبتا","محمد صادق",descriptionMap.get(titles.get(8)).toString(),R.drawable.hybta));
            lstBook.add(new Book("اغتصاب ولكن تحت سقف واحد","دعاء عبد الرحمن",descriptionMap.get(titles.get(9)).toString(),R.drawable.aghtsab_olkn_tht_skf_oahd));
            lstBook.add(new Book("لا تسألني لماذا أحببتها","أحمد السعيد مراد",descriptionMap.get(titles.get(10)).toString(),R.drawable.la_tsalny_lmatha_ahbbtha));
            //lstBook.add(new Book("","Categorie Book","Description book",R.drawable.thewildrobot));
            // lstBook.add(new Book("","Categorie Book","Description book",R.drawable.mariasemples));
            //lstBook.add(new Book("","Categorie Book","Description book",R.drawable.themartian));
            //lstBook.add(new Book("","Categorie Book","Description book",R.drawable.hediedwith));

            bookDao.insert(lstBook);
            return null;
        }
    }

}
