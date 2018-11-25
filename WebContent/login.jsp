<!DOCTYPE html>
<!--[if lt IE 9 ]><html class="no-js oldie" lang="en"> <![endif]-->
<!--[if IE 9 ]><html class="no-js oldie ie9" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<%@page import="java.util.*" %>
<head>

    <!--- basic page needs
    ================================================== -->
    <meta charset="utf-8">
    <title>DBS Forex Trading</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- mobile specific metas
    ================================================== -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- CSS
    ================================================== -->
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/vendor.css">
    <link rel="stylesheet" href="css/main.css">

    <!-- script
    ================================================== -->
    <script src="js/modernizr.js"></script>
    <script src="js/pace.min.js"></script>

    <!-- favicons
    ================================================== -->
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="icon" href="favicon.ico" type="image/x-icon">

	
	
	
		<script src="//static.codepen.io/assets/editor/live/console_runner-ce3034e6bde3912cc25f83cccb7caa2b0f976196f2f2d52303a462c826d54a73.js"></script>
		<script src="//static.codepen.io/assets/editor/live/css_live_reload_init-e9c0cc5bb634d3d14b840de051920ac153d7d3d36fb050abad285779d7e5e8bd.js"></script>
		<meta charset="UTF-8"><meta name="robots" content="noindex">
		<link rel="shortcut icon" type="image/x-icon" href="//static.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico">
		<link rel="mask-icon" type="" href="//static.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111">
		<link rel="canonical" href="https://codepen.io/anon/pen/OoRzKX?editors=1100">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/css/materialize.min.css">
	
					<style class="cp-pen-styles">


							

							.container
							{
								margin-top: 80px;
								width: 400px;
								height: 700px;
							}

							.tabs .indicator
							{
								background-color: #424242;
								height: 60px;
								opacity: 0.2;
							}
							
							
							.form-container
							{
								padding: 40px;
								padding-top: 10px;
								
							}

							.confirmation-tabs-btn
							{
								position: absolute;
							}
						</style>
	
</head>

<body id="top">
<%
				Map<String, Double> bids = new HashMap<String, Double>();
				Map<String, Double> asks = new HashMap<String, Double>();
				bids.put("EUR/USD",0.0);bids.put("EUR/GBP",0.0);bids.put("EUR/CHF",0.0);bids.put("GBP/USD",0.0);
				bids.put("EUR/AUD",0.0);bids.put("GBP/NZD",0.0);bids.put("NZD/CAD",0.0);bids.put("TRY/JPY",0.0);
				
				asks.put("EUR/USD",0.0);asks.put("EUR/GBP",0.0);asks.put("EUR/CHF",0.0);asks.put("GBP/USD",0.0);
				asks.put("EUR/AUD",0.0);asks.put("GBP/NZD",0.0);asks.put("NZD/CAD",0.0);asks.put("TRY/JPY",0.0);
				
				session.setAttribute("bids",bids);
				session.setAttribute("asks",asks);
				
				
				String success= (String)request.getSession().getAttribute("registerSuccess");
				String fail= (String)request.getSession().getAttribute("loginFail");
				if(success !=  null){
					out.println(success);
					request.getSession().setAttribute("registerSuccess", null);
				}
				else if(fail !=  null){
					out.println(fail);
					request.getSession().setAttribute("loginFail", null);
				}
	%>

    <!-- header
    ================================================== -->
    <header class="s-header">
	
        <!--div class="header-logo" style="height:70px; width:100%; background-color:black" >
            <a class="site-logo" href="index.html">
                <img src="images/logo4.png" alt="Homepage" >
            </a>
        </div-->
		<div style="background-color:black; height:80px; width:100%; opacity:0.8; position:fixed">
		 <a class="site-logo" href="index.html" >
                <img src="images/logo4.png" alt="Homepage" style="height:70px; width:10%" >
            </a>
		</div>

				
		
        <!--nav class="header-nav">

            <a href="#0" class="header-nav__close" title="close"><span>Close</span></a>

            <div class="header-nav__content">
                <h3>Navigation</h3>
                
                <ul class="header-nav__list">
                    <li class="current"><a class="smoothscroll"  href="#home" title="home">Home</a></li>
                    <li><a class="smoothscroll"  href="#about" title="about">About</a></li>
                    <li><a class="smoothscroll"  href="#services" title="services">Services</a></li>
                    <li><a class="smoothscroll"  href="#works" title="works">Works</a></li>
                    <li><a class="smoothscroll"  href="#clients" title="clients">Clients</a></li>
                    <li><a class="smoothscroll"  href="#contact" title="contact">Contact</a></li>
                </ul>
				
					

    
                <p>DBS Forex Trading will allow you to trade seamlessly and provide insights that will help you make informed decisions. </p>
    
                <ul class="header-nav__social">
                    <li>
                        <a href="#"><i class="fa fa-facebook"></i></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                    </li>
                    
                </ul>

            </div> <end header-nav__content>

        </nav>  <!-- end header-nav -->

        <!--a class="header-menu-toggle" href="#0">
            <span class="header-menu-text">login/register</span>
            <span class="header-menu-icon"></span>
        </a-->

    </header> <!-- end s-header -->


    <!-- home
    ================================================== -->
    <section id="home" class="s-home target-section" data-parallax="scroll" data-image-src="images/bg1.jpeg" data-natural-width=3000 data-natural-height=2000 data-position-y=center>

        <div class="overlay"></div>
        <div class="shadow-overlay"></div>

        <div class="home-content">
            <div class="row home-content__main">

                <h2 style="color:red">Welcome to DBS</h2>

                <h3 style="color:white">
                    Engineered To Flow. <br>
                    Get More Out Of<br>
                    Every Online Trade <br>
                    With DBS Forex
                </h3>

               

            </div>

            <!-- <div class="home-content__scroll">
                <a href="#about" class="scroll-link smoothscroll">
                    <span>Scroll Down</span>
                </a>
            </div>

            <div class="home-content__line"></div>
 -->
        </div> <!-- end home-content -->


        <!--ul class="home-social">
            <li>
                <a href="#0"><i class="fa fa-facebook" aria-hidden="true"></i><span>Facebook</span></a>
            </li>
            <li>
                <a href="#0"><i class="fa fa-twitter" aria-hidden="true"></i><span>Twiiter</span></a>
            </li>
            <li>
                <a href="#0"><i class="fa fa-instagram" aria-hidden="true"></i><span>Instagram</span></a>
            </li>
            
        </ul--> 
        <!-- end home-social -->
	
		<html class="" lang="en">
		<head>
		
						
							</head>
							<body style="background-color:transparent">
							<div class="container white z-depth-2">
								<ul class="tabs red" style="width: 100%;">
									<li class="tab col s3"><a class="white-text active" href="#login"><font size="4" weight="300">login</font></a></li>
									<li class="tab col s3"><a class="white-text" href="#register"><font size="4">register</font></a></li>
								<div class="indicator" style="right: 200px; left: 0px;"></div></ul>
								<div id="login" class="col s12" >
									<form class="col s2" action="LoginServlet" method="post">
										<div class="form-container">
											<!--h3 class="teal-text">Hello</h3-->
											<div class="row">
												<div class="input-field col s12">
													<input name="userId" type="text" class="validate">
													<label for="icon_prefix">User ID</label>
												</div>
											</div>
											<div class="row">
												<div class="input-field col s12">
												<input name="password" type="password" class="validate">
												<label for="icon_prefix">Password</label>
												</div>
											</div>
											<br>
											
												<button class="btn waves-effect waves-light red" type="submit" name="action">LOGIN</button>
												<br>
												<br>
												<!--a href="">Forgotten password?</a-->
										
										</div>
									</form>
								</div>
								<div id="register" class="col s12" style="display: none;">
				<form class ="col s12" action="RegisterServlet" method="post" onsubmit="return formValidation();">
					<div class="form-container"><br><br>
					<% 
						fail= (String)request.getSession().getAttribute("registerFail");
						if(fail !=  null){
							out.println(fail);
							request.getSession().setAttribute("registerFail", null);
						}
					%>
						<!--h3 class="teal-text">Welcome</h3-->
						<div class="row">
							<div class="input-field col s6">
								<input type="text" id="username" name="userName"/ required>
								<label for="usernmae">Username</label>
							</div>
							<div class="input-field col s6">
								<input type="password" id="password" name="password"/ required>
								<label for="password">password</label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12">
								<label for="password">Re-enter Password</label>
								<input type="password" id="repassword"/ required>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12">
								<label for="number">Govt ID</label>
								<input type="number" id="govtId" name="govtId"/ required>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s6">
								<input type="number" id="phoneNo" name="phoneNo"/ required>
								<label for="number">Phone Number</label>
							</div>
							<div class="input-field col s6">
								<input  type="number" id="bankNo" name="bankNo"/ required>
								<label for="number">Account Number</label>
							</div>
						</div>
						<div class="row">
							<div class="input-field col s12">
								<input type="email" id="email" name="emailId"/ required>
								<label for="email">Email</label>
							</div>
						</div>
						
												

							<button class="btn waves-effect waves-light red center" type="submit" name="action">Register</button>
						
					</div>
				</form>
			</div>
							</div>
							<script src="//static.codepen.io/assets/common/stopExecutionOnTimeout-41c52890748cd7143004e05d3c5f786c66b19939c4500ce446314d1748483e13.js"></script>
							<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
							<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.5/js/materialize.min.js"></script>
							<script>//using materializecss..
							//# sourceURL=pen.js
							</script>
							<div class="hiddendiv common"></div></body></html>
		
		
    </section> <!-- end s-home -->


 


    

       <!-- contact
    ================================================== -->
    <section id="contact" class="s-contact">

        <div class="overlay"></div>
        <!-- <div class="contact__line"></div> -->

        <div class="row section-header" data-aos="fade-up">
            <div class="col-full">
                <h3 class="subhead">Contact Us</h3>
                <h1 class="display-2 display-2--light">Reach out to us for a new trade or just say hello</h1>
            </div>
        </div>

        <div class="row contact-content" data-aos="fade-up">
            
            <div class="contact-primary">

                <h3 class="h6">Send Us A Message</h3>

                <form name="contactForm" id="contactForm" method="post" action="" novalidate="novalidate">
                    <fieldset>
    
                    <div class="form-field">
                        <input name="contactName" type="text" id="contactName" placeholder="Your Name" value="" minlength="2" required="" aria-required="true" class="full-width">
                    </div>
                    <div class="form-field">
                        <input name="contactEmail" type="email" id="contactEmail" placeholder="Your Email" value="" required="" aria-required="true" class="full-width">
                    </div>
                    <div class="form-field">
                        <input name="contactSubject" type="text" id="contactSubject" placeholder="Subject" value="" class="full-width">
                    </div>
                    <div class="form-field">
                        <textarea name="contactMessage" id="contactMessage" placeholder="Your Message" rows="10" cols="50" required="" aria-required="true" class="full-width"></textarea>
                    </div>
                    <div class="form-field">
                        <button class="full-width btn--primary">Submit</button>
                        <div class="submit-loader">
                            <div class="text-loader">Sending...</div>
                            <div class="s-loader">
                                <div class="bounce1"></div>
                                <div class="bounce2"></div>
                                <div class="bounce3"></div>
                            </div>
                        </div>
                    </div>
    
                    </fieldset>
                </form>

                <!-- contact-warning -->
                <div class="message-warning">
                    Something went wrong. Please try again.
                </div> 
            
                <!-- contact-success -->
                <div class="message-success">
                    Your message was sent, thank you!<br>
                </div>

            </div> <!-- end contact-primary -->

            <div class="contact-secondary">
                <div class="contact-info">

                    <h3 class="h6 hide-on-fullwidth">Contact Info</h3>

                    <div class="cinfo">
                        <h5>Where to Find Us</h5>
                        <p>
                            15th Floor, Tower 2.1,<br>
                            TSI Waverock IT/ITES SEZ<br>
                            Nanakramguda, Gachibowli
                        </p>
                    </div>

                    <div class="cinfo">
                        <h5>Email Us At</h5>
                        <p>
                            dacomms@1bank.dbs.com<br>
                            amitsarma@1bank.dbs.com
                        </p>
                    </div>

                    <div class="cinfo">
                        <h5>Call Us At</h5>
                        <p>
                            Phone: +91-40-67522222<br>
                            Mobile: +91-8328157536<br>
                            
                        </p>
                    </div>

                    <ul class="contact-social">
                        <li>
                            <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                        </li>
                        
                        
                    </ul> <!-- end contact-social -->

                </div> <!-- end contact-info -->
            </div> <!-- end contact-secondary -->

        </div> <!-- end contact-content -->
		 <div class="row footer-main" >

            <div class="col-six tab-full left footer-desc" style="color:white">

               
                At DBS Forex Trading, we believe that trading tomorrow will look fundamentally different from trading today. You will experience a whole new level of trading. Customer Satisfaction is our motto.Join us in shaping the future of trading.

            </div>

            <div class="col-six tab-full right footer-subscribe" >

                <h4 style="color:white">Get Notified</h4>
                <p style="color:white">Don't Miss Out Our NewsFeed. We've got something interesting for everyone!</p>

                <div class="subscribe-form">
                    <form id="mc-form" class="group" novalidate="true" >
                        <input type="email" value="" name="EMAIL" class="email" id="mc-email" placeholder="Email Address" required="">
                        <input type="submit" name="subscribe" value="Subscribe">
                        <label for="mc-email" class="subscribe-message"></label>
                    </form>
                </div>

            </div>

        </div> <!-- end footer-main -->

    </section> <!-- end s-contact -->


    


    <!-- photoswipe background
    ================================================== -->
    <div aria-hidden="true" class="pswp" role="dialog" tabindex="-1">

        <div class="pswp__bg"></div>
        <div class="pswp__scroll-wrap">

            <div class="pswp__container">
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
                <div class="pswp__item"></div>
            </div>

            <div class="pswp__ui pswp__ui--hidden">
                <div class="pswp__top-bar">
                    <div class="pswp__counter"></div><button class="pswp__button pswp__button--close" title="Close (Esc)"></button> <button class="pswp__button pswp__button--share" title=
                    "Share"></button> <button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button> <button class="pswp__button pswp__button--zoom" title=
                    "Zoom in/out"></button>
                    <div class="pswp__preloader">
                        <div class="pswp__preloader__icn">
                            <div class="pswp__preloader__cut">
                                <div class="pswp__preloader__donut"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
                    <div class="pswp__share-tooltip"></div>
                </div><button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)"></button> <button class="pswp__button pswp__button--arrow--right" title=
                "Next (arrow right)"></button>
                <div class="pswp__caption">
                    <div class="pswp__caption__center"></div>
                </div>
            </div>

        </div>

    </div> <!-- end photoSwipe background -->


    <!-- preloader
    ================================================== -->
    <div id="preloader">
        <div id="loader">
            <div class="line-scale-pulse-out">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>
    </div>


    <!-- Java Script
    ================================================== -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/plugins.js"></script>
    <script src="js/main.js"></script>

</body>

</html>