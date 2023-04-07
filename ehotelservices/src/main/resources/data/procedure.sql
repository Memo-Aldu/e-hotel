set search_path TO ehotel;
/* Adding the price of the extension to the total price */
CREATE OR REPLACE FUNCTION stay_extension_totalling() RETURNS TRIGGER LANGUAGE PLPGSQL AS
$$
BEGIN
    UPDATE appdb.ehotel.stay S
    SET payment_total = S.payment_total + E.extension_price
    FROM appdb.ehotel.extension AS E
    WHERE E.extension_id  = new.extension_id AND S.stay_id = new.stay_id;
    RETURN new;
END;
$$;
/* Adding the price of the extension to the total price */
CREATE OR REPLACE FUNCTION reservation_extension_totalling() RETURNS TRIGGER LANGUAGE PLPGSQL AS
$$
BEGIN
    UPDATE appdb.ehotel.reservation R
    SET total_price = R.total_price + E.extension_price
    FROM appdb.ehotel.extension AS E
    WHERE E.extension_id  = new.extension_id AND R.reservation_id = new.reservation_id;
    RETURN new;
END;
$$;
/* Creating the triggers */
DROP TRIGGER IF EXISTS stay_extension_totalling_trigger ON appdb.ehotel.extension_stay;
CREATE TRIGGER stay_extension_totalling_trigger
    AFTER INSERT ON appdb.ehotel.extension_stay
    FOR EACH ROW EXECUTE PROCEDURE stay_extension_totalling();

DROP TRIGGER IF EXISTS reservation_extension_totalling_trigger ON appdb.ehotel.extension_reservation;
CREATE TRIGGER reservation_extension_totalling_trigger
    AFTER INSERT ON appdb.ehotel.extension_reservation
    FOR EACH ROW EXECUTE PROCEDURE reservation_extension_totalling();

