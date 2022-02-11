import classes from "./HomeSummary.module.css";

const HomeSummary = () => {
  return (
    <section className={classes.summary}>
      <h2>Reaching for the stars?</h2>
      <p>
        <br />
        Choose your favorite form of SPACE travel <br />
        from our broad selection of available coupons
        <br />
        and explore the Solar System!
        <br />
      </p>
      <p>
        One small step for your wallet,
        <br />
        One giant leap for mankind!
      </p>
    </section>
  );
};

export default HomeSummary;
