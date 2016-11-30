<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Dongguk University 2016-2 Web Project</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/modern-business.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">Just Book</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            
            
            <form class="navbar-form navbar-left" role="search">
              <div class="form-group">
              <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 강의명 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="portfolio-1-col.html">1 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-2-col.html">2 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-3-col.html">3 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-4-col.html">4 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-item.html">Single Portfolio Item</a>
                            </li>
                        </ul>
                    </li>
                <input type="text" class="form-control" placeholder="Search">
                <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>        
              </div>
            </form>
            
           
            
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="" data-target="#modal_Login" type="button"  data-toggle="modal">Login</a>
                    </li>
                    <li>
                        <a href="about.html">new</a>
                    </li>
                    <li>
                        <a href="contact.html">Hot</a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Q&A <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="portfolio-1-col.html">1 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-2-col.html">2 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-3-col.html">3 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-4-col.html">4 Column Portfolio</a>
                            </li>
                            <li>
                                <a href="portfolio-item.html">Single Portfolio Item</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">MyPage <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="blog-home-1.html">Blog Home 1</a>
                            </li>
                            <li>
                                <a href="blog-home-2.html">Blog Home 2</a>
                            </li>
                            <li>
                                <a href="blog-post.html">Blog Post</a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Category <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="full-width.html">Full Width Page</a>
                            </li>
                            <li>
                                <a href="sidebar.html">Sidebar Page</a>
                            </li>
                            <li>
                                <a href="faq.html">FAQ</a>
                            </li>
                            <li>
                                <a href="404.html">404</a>
                            </li>
                            <li>
                                <a href="pricing.html">Pricing Table</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    
    
<div class="xe-widget-wrapper " style=""><div style="*zoom:1;padding:0px 0px 0px 0px !important;"><div class="modal" id="modal_Login">
   <div class="modal-dialog modal-sm">
      <div class="modal-content">
         <div class="modal-header">
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
               </button>
               <h2 class="modal-title text-center fc-orange">로그인</h2>
            </div>
            <div class="modal-body">
                <form id="fo_login_widget" action="/index.php?act=procMemberLogin" method="post"  role="form" class="clearfix"><input type="hidden" name="error_return_url" value="/study/2599" /><input type="hidden" name="mid" value="study" /><input type="hidden" name="vid" value="" /><input type="hidden" name="ruleset" value="@login" />
               <fieldset id="acField">
                  <input type="hidden" name="act" value="procMemberLogin" />
                  <input type="hidden" name="success_return_url" value="/study/2599" />
                  <input type="hidden" name="xe_validator_id" value="widgets/login_info/skins/login_skin_designsol/login_form/1" />
                                    <div>
                     <div class="form-group">
                                                                     </div>
                     <div class="form-group">
                        <label for="user_id">아이디</label>         
                        <input name="user_id" id="user_id" type="id" required class="form-control" placeholder="Your ID" />   </div>
                     <div class="form-group">
                        <label for="user_pw">비밀번호</label>
                        <input name="password" id="user_pw" class="form-control" type="password" required placeholder="Password" />
                     </div>
                     <div class="form-group keep-s">
                                                <input type="checkbox" name="keep_signed" id="keep_signed" value="Y" />
                                                <label for="keep_signed">로그인 유지</label>
                                          </div>
                     <ul class="help list-unstyled list-inline">
                        <li>
                           <a href="/index.php?mid=study&amp;document_srl=2599&amp;act=dispMemberSignUpForm">회원가입</a>
                        </li>
                        <li>
                           <a href="/index.php?mid=study&amp;document_srl=2599&amp;act=dispMemberFindAccount">ID/PW 찾기</a>
                        </li>
                     </ul>
                     <button type="submit" class="butn butn-warning butn-block">
                        로그인                     </button>
                  </div>
               </fieldset>
            </form>
            </div>
         </div><!-- modal-content -->
   </div><!-- modal-dialog -->
</div><!-- modal --></div></div>


    <!-- Header Carousel -->
    <header id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
                <div class="fill" style="background-image:url('img/bg1.jpg');"></div>
                <div class="carousel-caption">
                    <h2>Best Seller 1</h2>
                </div>
            </div>
            <div class="item">
                <div class="fill" style="background-image:url('img/bg2.jpg');"></div>
                <div class="carousel-caption">
                    <h2>Best Seller 2</h2>
                </div>
            </div>
            <div class="item">
                <div class="fill" style="background-image:url('img/bg3.jpg');"></div>
                <div class="carousel-caption">
                    <h2>Best Seller 3</h2>
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
    </header>

   <!-- Team Members Row >
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Our Team</h2>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://placehold.it/200x200" alt="">
                <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://placehold.it/200x200" alt="">
                <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://placehold.it/200x200" alt="">
                <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://placehold.it/200x200" alt="">
                <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://placehold.it/200x200" alt="">
                <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
            <div class="col-lg-4 col-sm-6 text-center">
                <img class="img-circle img-responsive img-center" src="http://placehold.it/200x200" alt="">
                <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
            </div>
        </div-->



    <!-- Page Content -->
    
    <div class="container">

        <!-- Marketing Icons Section -->
       
        <div class="row">
           <div class="col-lg-12">
                <center><h2 class="page-header">인기 강좌 목록</h2></center>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/MeB4U.jpg" alt="">
                    <h3>me Before you
                    <small>by Jojo Moyes</small>
                </h3>
                <p>Louisa Clark is an ordinary girl living an exceedingly ordinary life—steady boyfriend, close family—who has barely been farther afield than their tiny village. She takes a badly needed job working for ex–Master of the Universe Will Traynor, who is wheelchair bound after an accident.</p>
                </a>
            </div>
            
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/Peter.jpg" alt="">
                    <h3>Peter Pan
                    <small>by RH Disney</small>
                </h3>
                <p>Peter Pan and his fairy friend Tinker Bell are back in this vintage Little Golden Book! Featuring gorgeous illustrations from 1952, this children's classic is being reissued just in time for the release of Walt Disney's Peter Pan Platinum Edition DVD</p>
                </a>
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/SomeDay.jpg" alt="">
                    <h3>Someday
                    <small>by Alison McGhee</small>
                </h3>
                <p>A deceptively simple, powerful ode to the potential of love and the potential in life, Someday is the book you'll want to share with someone else...today. The perfect gift for Mother's Day, Graduation Day or Any Day - share a copy with every special person in your life.</p>
                </a>
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/grit.jpg" alt="">
                    <h3>GRIT
                    <small>by Angela Duckworth</small>
                </h3>
                <p>In this instant New York Times bestseller, pioneering psychologist Angela Duckworth shows anyone striving to succeed—be it parents, students, educators, athletes, or business people—that the secret to outstanding achievement is not talent but a special blend of passion and persistence she calls “grit.”</p>
                </a>
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/MrsFR.jpg" alt="">
                    <h3>Miss peregrine's home for peculiar child
                    <small>by Ransom Riggs</small>
                </h3>
                <p>It all waits to be discovered in Miss Peregrine’s Home for Peculiar Children, an unforgettable novel that mixes fiction and photography in a thrilling reading experience. As our story opens, a horrific family tragedy sets sixteen-year-old Jacob journeying to a remote island off the coast of Wales, where he discovers the crumbling ruins of Miss Peregrine’s Home for Peculiar Children.</p>
                </a>
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/WBB.jpg" alt="">
                    <h3>When Breath Becomes Air
                    <small>by Paul Kalanithi</small>
                </h3>
                <p>At the age of thirty-six, on the verge of completing a decade’s worth of training as a neurosurgeon, Paul Kalanithi was diagnosed with stage IV lung cancer. One day he was a doctor treating the dying, and the next he was a patient struggling to live.</p>
                </a>
                </a>
            </div>
        </div>
        
        <!-- /.row -->


   


        <!-- Portfolio Section >
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">2016 Bestseller books List</h2>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/MeB4U.jpg" alt="">
                    <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/Peter.jpg" alt="">
                    <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
                </a>
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/SomeDay.jpg" alt="">
                    <h3>me Before you
                    <small>by Jojo Moyes</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
                </a>
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/grit.jpg" alt="">
                    <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
                </a>
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/MrsFR.jpg" alt="">
                    <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
                </a>
                </a>
            </div>
            <div class="col-md-4 col-sm-6">
                <a href="portfolio-item.html">
                    <img class="img-responsive img-portfolio img-hover" src="img/WBB.jpg" alt="">
                    <h3>John Smith
                    <small>Job Title</small>
                </h3>
                <p>What does this team member to? Keep it short! This is also a great spot for social links!</p>
                </a>
                </a>
            </div>
        </div>
       < /.row --> 

        <!-- Features Section -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Modern Business Features</h2>
            </div>
            <div class="col-md-6">
                <p>The Modern Business template by Start Bootstrap includes:</p>
                <ul>
                    <li><strong>Bootstrap v3.3.7</strong>
                    </li>
                    <li>jQuery v1.11.1</li>
                    <li>Font Awesome v4.2.0</li>
                    <li>Working PHP contact form with validation</li>
                    <li>Unstyled page elements for easy customization</li>
                    <li>17 HTML pages</li>
                </ul>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Corporis, omnis doloremque non cum id reprehenderit, quisquam totam aspernatur tempora minima unde aliquid ea culpa sunt. Reiciendis quia dolorum ducimus unde.</p>
            </div>
            <div class="col-md-6">
                <img class="img-responsive" src="http://placehold.it/700x450" alt="">
            </div>
        </div>
        <!-- /.row -->

        <hr>

        <!-- Call to Action Section -->
        <div class="well">
            <div class="row">
                <div class="col-md-8">
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Molestias, expedita, saepe, vero rerum deleniti beatae veniam harum neque nemo praesentium cum alias asperiores commodi.</p>
                </div>
                <div class="col-md-4">
                    <a class="btn btn-lg btn-default btn-block" href="#">Call to Action</a>
                </div>
            </div>
        </div>

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Script to Activate the Carousel -->
    <script>
    $('.carousel').carousel({
        interval: 5000 //changes the speed
    })
    </script>

</body>

</html>