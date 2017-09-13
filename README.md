# SLS for ROBLOX

**SLS** or otherwise known as **Server List Server** is a component in the **User Server List
Revival** project that was started by PizzaCrust to replace the removed 'server list feature' in
ROBLOX. This feature was very important to me, and thus I have created this server in tandem with
several
more components:

- **SLC** (Server List Client), auto registration to SLS. Check repositories of USLR to see releases/build tutorials.
- **SLH** (Server List Hub), automatic and seamless hub to automatically use SLS for universal-able
games. Currently in private beta.
- **SLB** (Server List Browser), unofficial automatic integration into the ROBLOX website with a
central SLS
 server using a browser extension and removes the current limits that the SLH can do. Coming soon,
  to
 chrome and opera.

In order to replace and provide an alternative to a feature that I liked, and can be applied easily.

## Tutorial

You need to host this web server on a solution, similar to heroku. The repository is already
setup for simple deployment using Heroku. You just need:

- Git (must be on environmental path and have credentials)
- Heroku account
- Heroku CLI (must be on environmental path and have credentials to your heroku account)
- Windows (may work on Macintosh, haven't tested)

After you have installed/registered the requirements, the following are steps.

1. Clone this GitHub repository.
2. Run 'heroku create' in a command prompt (assuming that you already have heroku in your
environmental path.) If you haven't entered your credentials into the heroku command line, it will
 ask you to submit your login credentials.
3. Run 'git push heroku master' (assuming that you already have git in your environmental path
and credentials placed).
4. Run 'heroku logs --tail', you will see a segment called **code**, this is your authentication
code which changes every time you restart/push a new update to the system. Take note of it, to
setup SLC. After that, make sure you capture the heroku subdomain for SLC, also. Press CTRL+C and
 enter Y, to exit out of the selection.
5. Enjoy your server!